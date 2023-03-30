import requests



# endpoint = "http://localhost:8000/api/get/SELECT/DoH/None/None/None"
# result = requests.get(endpoint).json()
# c=0
# for r in result:
#     print(r['p'])
#     c=c+1
#     if c==5: 
#         break
    
    
    
print("\n")

endpoint1 = "http://localhost:8000/api/get/SELECT/DoH/None/None/True"
result1 = requests.get(endpoint1).json()
c=0 
for r in result1:
    print("\nSUBJECT: " ,r["s"])
    print("PREDICAT: " ,r["p"])
    print("OBJECT: " ,r["o"])