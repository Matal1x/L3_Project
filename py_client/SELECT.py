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

endpoint1 = "http://localhost:8000/api/get/SELECT/DNS/Attack1/None/192.168.20.191/"
endpoint2 = "http://localhost:8000/api/get/SELECT/NSLKDD/Attack1/None/0/"
result = requests.get(endpoint1).json()
c=0 
for r in result:
    print("\nSUBJECT: " ,r["s"])
    print("PREDICAT: " ,r["p"])
    print("OBJECT: " ,r["o"])