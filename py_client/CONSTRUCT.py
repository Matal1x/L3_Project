import requests



# endpoint = "http://localhost:8000/api/get/CONSTRUCT/DoH/None/None/None"
# result = requests.get(endpoint).json()
# print(result)


endpoint1 = "http://localhost:8000/api/get/CONSTRUCT/DoH/None/DoH/0"
result1 = requests.get(endpoint1)
print(result1.json())