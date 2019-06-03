from flask import Flask
from flask import request
from flask import jsonify
import requests
import  json
import re
from flask_sslify import SSLify


app = Flask(__name__)
sslify = SSLify(app)


URL = 'https://api.telegram.org/bot887973141:AAEXAKYUYQeajxK75AwrdZTSIywplpAmisY/'


def write_json(data, filename='answer.json'):
    with open(filename, 'w') as f:
        json.dump(data, f, indent=2, ensure_ascii=False)


def send_message(chat_id, text='bla-bla-bla'):
    url = URL + 'sendMessage'
    answer = {'chat_id': chat_id, 'text': text}
    r = requests.post(url, data=answer)
    return r.json()


def parse_lang(text):
    pattern = r'/\w+'
    lang = re.search(pattern, text).group()[1:]
    # print(lang)
    return lang


def parse_text(text):
    text = str(text).split()[1:]
    # print(text)
    return str(text)


def translate(text, lang):
    url = 'https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20190528T092312Z.9f06ddefe7319609.f74ef3d774a2b73992d6db891079a28d9b2aeb55' + '&text={}'.format(text) + '&lang={}'.format(lang)
    r = requests.get(url).json()
    word = r['text']
    # print(url)
    return word[0]


@app.route('/', methods=['POST', 'GET'])
def index():
    if request.method == 'POST':
        r = request.get_json()
        chat_id = r['message']['chat']['id']
        message = r['message']['text']

        pattern = '/\w+'

        if re.search(pattern, message):
            word = translate(parse_text(message), parse_lang(message))
            send_message(chat_id, text=word)

        # write_json(r)

        return jsonify(r)
    return '<h1>Bot welcomes you</h1>'


if __name__ == '__main__':
    app.run()
