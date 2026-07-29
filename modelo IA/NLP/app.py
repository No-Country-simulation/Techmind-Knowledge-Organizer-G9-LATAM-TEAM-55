from fastapi import FastAPI
from pydantic import BaseModel

from predecir import predecir

app = FastAPI()

class Contenido(BaseModel):
    titulo: str
    texto: str

@app.post("/predict")
def predict(data: Contenido):

    texto_limpio, categoria, confianza = predecir(
        data.titulo,
        data.texto
    )

    return {
        "categoria": categoria,
        "confianza": confianza,
        "texto_procesado": texto_limpio
    }