import requests

"""
endpoint = "http://localhost:8000/api/get/" #http://127.0.0.1:8000/

result = requests.get(endpoint, params={"subject": "", "predicat": "SourceIP", "objectV": ""}).json()
result = result["rdf_result"]
print(result)

second_endpoint="http://localhost:8000/api/get/?subject=8&predicat=DestinationIP&objectV="

second_result=requests.get(second_endpoint).json()
second_result=second_result["rdf_result"]
print(second_result)

"""
# endpoint3 = "http://localhost:8000/api/get/3/DoH/None/"

# third_result = requests.get(endpoint3).json()
# print(third_result)


end= "http://localhost:8000/api/delete/10/None/None/"

print(requests.delete(end))