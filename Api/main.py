from flask import Flask, request, jsonify
from transformers import pipeline

generation_pipeline = pipeline('text-generation', model="fatawa", tokenizer='aubmindlab/aragpt2-base')



def generate_response(input_text):
    response = generation_pipeline(input_text, max_length=128)[0]['generated_text']
    start_index = response.index('answer:')
    if 'والله أعلم' in response:
        stop_index = response.index('والله أعلم')
        response = response[start_index+8:stop_index+11].strip()
    else:
        response = response[start_index+8:].strip()
    return response


app = Flask(__name__)


@app.route('/chat', methods=['GET', 'POST'])
def chatBot():
    chatInput = request.form['chatInput']
    print(chatInput)
    chatBotRepl= generate_response(chatInput)
    return chatBotRepl


if __name__ == '__main__':
     app.run(host='0.0.0.0')