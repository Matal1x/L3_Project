import requests
from django.shortcuts import render

from .forms import DoHTripleForm, KDDTripleForm

from rdflib import Graph as RDFGraph
from rdflib.extras.external_graph_libs import rdflib_to_networkx_graph
from pyvis.network import Network
# Create your views here.



def index(request):
        return render(request,'templates/triples/index.html')



#****************************************** DNS ******************************************************************

def DoHform(request):
      
      form=DoHTripleForm()
      found = False
      graph=""
      JSONArray=[]
        
      if request.method == 'POST':
            form= DoHTripleForm(request.POST)
            if form.is_valid():
                  print("validated data")
                  if form.cleaned_data['subject'] == "":
                      form.cleaned_data['subject']="None"
                      
                  sub = form.cleaned_data['subject']      
                      
                  if form.cleaned_data['predicat'] == "None":
                      form.cleaned_data['predicat']="None"
                
                  pre = form.cleaned_data['predicat']      
                      
                  obj = "None"       
                      
                  print("subject:", sub)
                  print("predicate:", pre)
                  print("objectV:", obj)
                
                  print(f"http://localhost:8000/api/get/CONSTRUCT/DNS/{sub}/{pre}/{obj}")
                
                  RESULT=requests.get(f"http://localhost:8000/api/get/CONSTRUCT/DNS/{sub}/{pre}/{obj}")
                  if RESULT.status_code == 200:
                      found = True 
                      graph=RESULT.json()
                      
                      #Retreiving data as JSON objects array
                      JSONArray=requests.get(f"http://localhost:8000/api/get/SELECT/DNS/{sub}/{pre}/{obj}").json()
                      
                      
                      
                      
                      
                      
                      #Visualizing the graph produced
                      temporary = RDFGraph()
                      temporary.parse(data=graph)
                      G = rdflib_to_networkx_graph(temporary)
                      net = Network(height="750px", width="100%", font_color="black")
                      net.from_nx(G)
                      net.write_html("templates\\generatedgraphs\\DoHtemp.html", local=False )
                                
                
      return render(request,'templates/triples/DoHform.html',{'form':form, 'found': found,'JSONArray':JSONArray})







def DoHgraph(r):
      return render(r,'templates/generatedgraphs/DoHtemp.html')


#****************************************** NSLKDD ******************************************************************

def KDDform(request):
      
      form=KDDTripleForm() #instance of a form
      found = False
      graph=""
      JSONArray=[]
        
      if request.method == 'POST':
            form= KDDTripleForm(request.POST)
            if form.is_valid():
                  print("validated data")
                  if form.cleaned_data['subject'] == "":
                      form.cleaned_data['subject']="None"
                      
                  sub = form.cleaned_data['subject']      
                      
                  if form.cleaned_data['predicat'] == "None":
                      form.cleaned_data['predicat']="None"
                
                  pre = form.cleaned_data['predicat']      

                 
                  obj = "None"    
                      
                  print("subject:", sub)
                  print("predicate:", pre)
                  print("objectV:", obj)
                
                  print(f"http://localhost:8000/api/get/CONSTRUCT/NSLKDD/{sub}/{pre}/{obj}")
                
                  RESULT=requests.get(f"http://localhost:8000/api/get/CONSTRUCT/NSLKDD/{sub}/{pre}/{obj}")
                  if RESULT.status_code == 200:
                      found = True 
                      graph=RESULT.json()
                      
                      #Retreiving data as JSON objects array
                      JSONArray=requests.get(f"http://localhost:8000/api/get/SELECT/NSLKDD/{sub}/{pre}/{obj}").json()
                      
                      
                      
                      
                      
                      
                      #Visualizing the graph produced
                      temporary = RDFGraph()
                      temporary.parse(data=graph)
                      G = rdflib_to_networkx_graph(temporary)
                      net = Network(height="750px", width="100%", font_color="black")
                      net.from_nx(G)
                      net.write_html("templates\\generatedgraphs\\KDDtemp.html", local=False )
                                
                
      return render(request,'templates/triples/KDDform.html',{'form':form, 'found': found,'JSONArray':JSONArray})

def KDDgraph(r):
      return render(r,'templates/generatedgraphs/KDDtemp.html')