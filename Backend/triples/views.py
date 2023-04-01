import requests
from django.shortcuts import render
from django.http import HttpResponseNotFound

from .forms import DoHTripleForm, KDDTripleForm

from rdflib import Graph as RDFGraph
from rdflib.extras.external_graph_libs import rdflib_to_networkx_graph
from pyvis.network import Network
# Create your views here.



def index(request):
        return render(request,'templates/triples/index.html')

def Myform(request, dataset):
      if dataset != "NSLKDD" and dataset != "DNS":
            return HttpResponseNotFound("No such DataSet available")
      
      found = False
      graph=""
      JSONArray=[]
        
      if dataset=="NSLKDD":
          form=KDDTripleForm()
      elif dataset =="DNS":
          form=DoHTripleForm()  
          
      if request.method == 'POST':
            
            if dataset=="NSLKDD":
                  form=KDDTripleForm(request.POST)
            elif dataset =="DNS":
                  form=DoHTripleForm(request.POST)  
            
            
            
            if form.is_valid():
                  print("validated data")
                  if form.cleaned_data['subject'] == "":
                      form.cleaned_data['subject']="None"
                  else:
                        form.cleaned_data['subject'] = "Attack"+form.cleaned_data['subject']
                      
                  sub = form.cleaned_data['subject']      
                      
                  if form.cleaned_data['predicat'] == "None":
                      form.cleaned_data['predicat']="None"
                
                  pre = form.cleaned_data['predicat']      

                 
                  obj = "None"    
                      
                  print("subject:", sub)
                  print("predicate:", pre)
                  print("objectV:", obj)
                
                  print(f"http://localhost:8000/api/get/CONSTRUCT/{dataset}/{sub}/{pre}/{obj}")
                
                  RESULT=requests.get(f"http://localhost:8000/api/get/CONSTRUCT/{dataset}/{sub}/{pre}/{obj}")
                  if RESULT.status_code == 200:
                      found = True 
                      graph=RESULT.json()
                      
                      #Retreiving data as JSON objects array
                      JSONArray=requests.get(f"http://localhost:8000/api/get/SELECT/{dataset}/{sub}/{pre}/{obj}").json()
                      
                      
                      
                      
                      
                      
                      #Visualizing the graph produced
                      temporary = RDFGraph()
                      temporary.parse(data=graph)
                      G = rdflib_to_networkx_graph(temporary)
                      net = Network(height="750px", width="100%", font_color="black")
                      net.from_nx(G)
                      net.write_html("templates\\generatedgraphs\\temp.html", local=False )
                                
                
      return render(request,'templates/triples/form.html',{'form':form, 'found': found, 'JSONArray':JSONArray, "data_set": dataset})

def graph(r):
      return render(r,'templates/generatedgraphs/temp.html')