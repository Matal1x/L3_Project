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
# endpoint3 = "http://localhost:8000/api/get/3/None/None/"

# third_result = requests.get(endpoint3).json()
# print(third_result)


# end= "http://localhost:8000/api/delete/36/None/None/"

# print(requests.delete(end))

# endp= "http://localhost:8000/api/put/36/DoH/True/"
headers = {'Authorization': 'Token 182a5a9bd73ece4b64a9a70d41e6d3908aca25c0'}
# print(requests.put(endp, headers=headers).json())


addgraphendpoint = "http://localhost:8000/api/put/graph/"
print(requests.post(addgraphendpoint, headers=headers, json={"rdf_text": '''@prefix ex: <http://example.org/> .\n\nex:36 ex:DestinationPort "55555" .\n\n''', "rdf_format": "turtle"}))