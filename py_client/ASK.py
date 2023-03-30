import requests


endpoint1 = "http://localhost:8000/api/get/ASK/DoH/None/None/True"
result1 = requests.get(endpoint1).json()
print(result1)