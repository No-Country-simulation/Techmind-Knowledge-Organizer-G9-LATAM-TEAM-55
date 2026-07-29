import sys
import re
import unicodedata
import joblib
import spacy
import ftfy

# ---------------------------------------------------------
# 1. Cargar modelo de spaCy y el modelo entrenado
# ---------------------------------------------------------
print("Cargando modelo de spaCy...")
nlp = spacy.load("es_core_news_sm", disable=["parser", "ner"])

RUTA_MODELO = "modelo_completo (2).joblib"
print(f"Cargando pipeline entrenado desde '{RUTA_MODELO}'...")

try:
    modelo_pipeline = joblib.load(RUTA_MODELO)
    print("¡Modelo cargado exitosamente!\n")
except FileNotFoundError:
    print(f"ERROR: No se encontró el archivo '{RUTA_MODELO}'. Asegúrate de que esté en la misma carpeta.")
    sys.exit(1)

# ---------------------------------------------------------
# 2. Funciones de Preprocesamiento
# ---------------------------------------------------------
def reparar_texto_corrupto(texto: str) -> str:
    if not isinstance(texto, str):
        return ""
    try:
        texto_arreglado = texto.encode('latin-1').decode('utf-8')
    except (UnicodeEncodeError, UnicodeDecodeError):
        texto_arreglado = texto
    return ftfy.fix_text(texto_arreglado)

def preprocesar_texto(texto: str) -> str:
    """Limpia el texto individual antes de pasar por spaCy."""
    if not isinstance(texto, str) or not texto.strip():
        return ""
    
    texto = reparar_texto_corrupto(texto)
    texto = texto.lower()
    
    # Quitar acentos
    texto = unicodedata.normalize('NFD', texto)
    texto = ''.join(c for c in texto if unicodedata.category(c) != 'Mn')

    # Mantener solo letras a-z
    texto = re.sub(r'[^a-z\s]', ' ', texto)
    texto = " ".join(texto.split())

    # Lematización con spaCy
    doc = nlp(texto)
    tokens = [
        token.lemma_ for token in doc 
        if not token.is_stop 
        and len(token.text) > 2 
        and token.pos_ in ['NOUN', 'PROPN', 'VERB', 'ADJ', 'ADV']
    ]
    return " ".join(tokens)

# ---------------------------------------------------------
# 3. Función Principal para Predicción
# ---------------------------------------------------------
def predecir(titulo: str, texto: str):
    # Unir entradas
    entrada_cruda = f"{titulo} {texto}".strip()
    
    # 1. Limpieza + NLP
    texto_procesado = preprocesar_texto(entrada_cruda)
    
    # 2. Predicción (El vectorizador TF-IDF y el modelo corren aquí)
    categoria_predicha = modelo_pipeline.predict([texto_procesado])[0]
    
    # Obtener probabilidades/confianza si el clasificador las soporta
    probabilidades = None
    if hasattr(modelo_pipeline, "predict_proba"):
        probs = modelo_pipeline.predict_proba([texto_procesado])[0]
        probabilidades = max(probs) * 100

    return texto_procesado, categoria_predicha, probabilidades

# ---------------------------------------------------------
# 4. Modo Interactivo por Consola
# ---------------------------------------------------------

"""

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
        texto_limpio, categoria, confianza = predecir(titulo_in, texto_in)

        # Mostrar Resultados
        print("-" * 50)
        print(f"Texto procesado (lemmas): \"{texto_limpio}\"")
        print(f"Categoría Predicha:       >>> {categoria.upper()} <<<")
        if confianza is not None:
            print(f"Nivel de Confianza:      {confianza:.2f}%")
        print("-" * 50)
"""""



# ---------------------------------------------------------
# 5. Modo Servidor Local 
# ---------------------------------------------------------


"""app.py"""