import requests
from django.shortcuts import render
from django.http import HttpResponseNotFound

from .forms import DoHTripleForm, KDDTripleForm


from rdflib import Graph as RDFGraph
from rdflib.extras.external_graph_libs import rdflib_to_networkx_graph
from pyvis.network import Network


import random
# Create your views here.



def index(request):
      return render(request,'templates/triples/index.html')

def Myform(request, dataset):
      
      if dataset != "NSLKDD" and dataset != "DNS":
            return HttpResponseNotFound("No such DataSet available")
      
      found = False
      notfound = False
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
                  if form.cleaned_data['subject'] == "" :
                      form.cleaned_data['subject']="None"
                  else:
                        form.cleaned_data['subject'] = "Attack"+form.cleaned_data['subject']
                        
                      
                  sub = form.cleaned_data['subject']      
                      
                  if form.cleaned_data['predicat'] == "None":
                      form.cleaned_data['predicat']="None"
                      
                  if form.cleaned_data['predicat'] == "":
                      form.cleaned_data['predicat']="None"
                
                  pre = form.cleaned_data['predicat']      

                 
                  if form.cleaned_data['objectV'] == "" :
                      form.cleaned_data['objectV']="None"
                  
                  obj = form.cleaned_data['objectV']     
                      
                  print("subject:", sub)
                  print("predicate:", pre)
                  print("objectV:", obj)
                
                  RESULT=requests.get(f"http://localhost:8000/api/get/SELECT/{dataset}/{sub}/{pre}/{obj}/")
                  if RESULT.status_code == 200:
                      found = True 
                      
                      #Retreiving data as JSON objects array
                      JSONArray=RESULT.json()
                        
                      #Visualizing the graph produced
                      net = Network(height="750px", width="100%", font_color="black", directed =True)
                      for r in JSONArray:
                            try:
                                  
                                  net.add_node(r['s'], r['s'], color='#00bf1e')
                                  net.add_node(r['o'], r['o'], color='#dd4b39')
                                  net.add_edge(r['s'], r['o'], label=r['p'] ,title=r['p'])
                            except:
                                  print("error while trying to create triple")
                      
                      
                      
                      net.add_node('ASLAOUI', 'ASLAOUI', color='#bdb7ab')
                      net.add_node('ABBAS', 'ABBAS', color='#ffc0cb')
                      net.add_node('MOKADDEM', "MOKADDEM", color='#0000FF')
                      
                      net.add_edge('ASLAOUI', 'ABBAS', label="colleague", title="colleague")
                      net.add_edge('ASLAOUI', 'MOKADDEM', label="mentor", title="mentor")
                      
                      net.add_edge("ABBAS", "ASLAOUI", label="colleague", title="colleague")
                      net.add_edge("ABBAS", "MOKADDEM", label="mentor", title="mentor")
                      
                      net.add_edge("MOKADDEM", "ASLAOUI", label="student", title="student")
                      net.add_edge("MOKADDEM", "ABBAS", label="student", title="student")
                      
                      
                      
                      
                      net.show_buttons(filter_=['physics'])
                      net.write_html("templates/generatedgraphs/temp.html", local=False )
                      
                  else:
                        notfound = True
                                
                
      return render(request,'templates/triples/form.html',{'form':form, 'found': found, 'notfound': notfound,'JSONArray':JSONArray, "data_set": dataset})

def graph(r):
      return render(r,'templates/generatedgraphs/temp.html')

#main-page contact page
