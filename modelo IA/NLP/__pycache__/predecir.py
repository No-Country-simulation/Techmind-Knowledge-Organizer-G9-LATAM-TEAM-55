import sys
import re
import unicodedata
import joblib
import spacy
import ftfy
import numpy as np

# =========================================================
# 1. Cargar modelo de spaCy y el Pipeline entrenado
# =========================================================
print("Cargando modelo de spaCy...")
nlp = spacy.load("es_core_news_sm", disable=["parser", "ner"])

RUTA_MODELO = "modelo_completo (5).joblib"
print(f"Cargando pipeline entrenado desde '{RUTA_MODELO}'...")

try:
    modelo_pipeline = joblib.load(RUTA_MODELO)
    print("¡Modelo cargado exitosamente!\n")
except FileNotFoundError:
    print(f"ERROR: No se encontró el archivo '{RUTA_MODELO}'. Asegúrate de que esté en la misma carpeta.")
    sys.exit(1)

# =========================================================
# 2. Configuración de NLP y Stopwords
# =========================================================
# Lista de palabras genéricas que bajan la confianza o hacen ruido
STOPWORDS_CUSTOM = {
    'presentar', 'sistema', 'gestion', 'permitir', 'usar', 'utilizar', 
    'realizar', 'mediante', 'traves', 'forma', 'ejemplo', 'tipo', 'opcion',
    'crear', 'hacer', 'tener', 'obtener', 'parte', 'contenido', 'concepto', 
    'basico', 'creacion', 'introduccion'
}

def reparar_texto_corrupto(texto: str) -> str:
    if not isinstance(texto, str):
        return ""
    try:
        texto_arreglado = texto.encode('latin-1').decode('utf-8')
    except (UnicodeEncodeError, UnicodeDecodeError):
        texto_arreglado = texto
    return ftfy.fix_text(texto_arreglado)

def preprocesar_texto(texto: str) -> str:
    """Limpia el texto individual conservando tecnicismos y quitando ruido neutro."""
    if not isinstance(texto, str) or not texto.strip():
        return ""
    
    texto = reparar_texto_corrupto(texto)
    texto = texto.lower()
    
    # Quitar acentos
    texto = unicodedata.normalize('NFD', texto)
    texto = ''.join(c for c in texto if unicodedata.category(c) != 'Mn')

    # Mantener solo letras a-z y espacios
    texto = re.sub(r'[^a-z\s]', ' ', texto)
    texto = " ".join(texto.split())

    # Lematización con spaCy y filtro de Stopwords Custom
    doc = nlp(texto)
    tokens = [
        token.lemma_ for token in doc 
        if not token.is_stop 
        and len(token.text) > 2 
        and token.lemma_ not in STOPWORDS_CUSTOM
    ]
    return " ".join(tokens)

def obtener_top_palabras_clave(texto_procesado: str, pipeline, top_n=3) -> list:
    """Extrae palabras clave únicas sin repetidos por superposición de n-gramas."""
    tfidf = pipeline.named_steps['tfidf']
    feature_names = np.array(tfidf.get_feature_names_out())
    
    vector_tfidf = tfidf.transform([texto_procesado])
    if vector_tfidf.nnz == 0:
        return []

    fila = vector_tfidf.toarray()[0]
    # Ordenar índices por peso TF-IDF descendente
    indices_ordenados = fila.argsort()[::-1]
    
    keywords_unicas = []
    for idx in indices_ordenados:
        if fila[idx] == 0:
            break
        candidato = feature_names[idx]
        
        # Lógica de Deduplicación: Evita incluir 'spring' si ya agregamos 'spring boot' (y viceversa)
        es_subtexto = any((candidato in kw) or (kw in candidato) for kw in keywords_unicas)
        if not es_subtexto:
            keywords_unicas.append(candidato)
            
        if len(keywords_unicas) == top_n:
            break
            
    return keywords_unicas

# =========================================================
# 3. Función Principal para Predicción
# =========================================================
def predecir(titulo: str, texto: str):
    # Unir entradas
    entrada_cruda = f"{titulo} {texto}".strip()
    
    # 1. Limpieza + NLP
    texto_procesado = preprocesar_texto(entrada_cruda)
    
    # 2. Predicción
    categoria_predicha = modelo_pipeline.predict([texto_procesado])[0]
    
    # Obtener probabilidades/confianza
    probabilidades = None
    if hasattr(modelo_pipeline, "predict_proba"):
        probs = modelo_pipeline.predict_proba([texto_procesado])[0]
        probabilidades = max(probs) * 100

    # 3. Extracción de las 3 palabras clave limpias
    palabras_clave = obtener_top_palabras_clave(texto_procesado, modelo_pipeline, top_n=3)

    return texto_procesado, categoria_predicha, probabilidades, palabras_clave

# =========================================================
# 4. Modo Interactivo por Consola
# =========================================================
if __name__ == "__main__":
    print("="*60)
    print("       PRUEBA DE INFERENCIA EN CONSOLA")
    print("="*60)
    print("Escribe 'salir' en el título para terminar el programa.\n")

    while True:
        titulo_in = input("\nIngrese el TÍTULO: ").strip()
        if titulo_in.lower() == 'salir':
            print("Saliendo del probador...")
            break
            
        texto_in = input("Ingrese el TEXTO/CUERPO: ").strip()

        if not titulo_in and not texto_in:
            print("[!] Por favor ingresa al menos un título o texto.")
            continue

        # Ejecutar Pipeline
        texto_limpio, categoria, confianza, palabras_clave = predecir(titulo_in, texto_in)

        # Mostrar Resultados
        print("-" * 50)
        print(f"Texto procesado (lemmas): \"{texto_limpio}\"")
        print(f"Categoría Predicha:       >>> {categoria.upper()} <<<")
        if confianza is not None:
            print(f"Nivel de Confianza:      {confianza:.2f}%")
        print(f"Top 3 Palabras Clave:    {palabras_clave}")
        print("-" * 50)