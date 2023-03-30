import requests



# endpoint = "http://localhost:8000/api/get/CONSTRUCT/DoH/None/None/None"
# result = requests.get(endpoint).json()
# print(result)


endpoint1 = "http://localhost:8000/api/get/CONSTRUCT/DoH/None/None/True"
result1 = requests.get(endpoint1).json()
print(result1)