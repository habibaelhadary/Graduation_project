from flask import Flask, request, jsonify
app = Flask(__name__)


@app.route('/one')
def one():
    return 'hello'
if __name__ == '__main__':
    app.run(host='0.0.0.0')

# app = Flask(_name_)
#
#
# @app.route('/')
# def hello_world():
#     return 'Hello, World!'
#
# if _name_ == '_main_':
#     app.run(debug=True)