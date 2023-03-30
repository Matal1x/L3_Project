from django.shortcuts import render
from .models import Triple
from .forms import TripleForm, rdf_queryForm
from django.views.generic import View,TemplateView
from rest_framework.decorators import api_view
from SPARQLWrapper import SPARQLWrapper, TURTLE, RDF
# Create your views here.

class MainGraphView(TemplateView):
      template_name='generatedgraphs/main.htmll'


def index(request):
        return render(request,'templates/triples/index.html')

# def form_name_view(request):
#         form=TripleForm() #instance of a form
#         checked = False
#         graph=""
#         if request.method == 'POST':
#             form= TripleForm(request.POST)
#             if form.is_valid():
#                 print("validated data")
#                 if form.cleaned_data['subject'] == "":
#                       form.cleaned_data['subject']=None
#                 if form.cleaned_data['predicat'] == "None":
#                       form.cleaned_data['predicat']=None
#                 if form.cleaned_data['objectV'] == "":
#                       form.cleaned_data['objectV']=None
#                 print("subject:", form.cleaned_data['subject'])
#                 print("predicate:", form.cleaned_data['predicat'])
#                 print("objectV:", form.cleaned_data['objectV'])
                
#                 graph=gfunc.get_graph(form.cleaned_data['subject'], form.cleaned_data['predicat'], form.cleaned_data['objectV'])
#                 checked = True 
                
            
            

#         return render(request,'templates/triples/formpage.html',{'form':form, 'checked': checked,'graph':graph }) #request,pass in th page i want to show,pass in the context dictionary: we'll give it the key form and we'll pass in that actual form object 
        
#         subject = gfunc.validator(form.cleaned_data['subject'])
#         predicat = gfunc.validator(form.cleaned_data['predicat'])
#         objectV = gfunc.object_validator(form.cleaned_data['objectV'])
        
#         main = SPARQLWrapper("our oracle endpoint")
#         main.setReturnFormat(TURTLE)
        
#         main.setQuerry(f"""
#                        PREFIX ex: <http://example.org/>
#                        CONSTRUCT ?subject ?predicat ?objectV
#                        WHERE {{
#                              ?subject ex:{predicat} ?objectV 
#                        }}
#                        """)
#         main.queryAndConvert()
        
      
      

# def render_graph(r):
#       return render(r, 'templates/generatedgraphs/temp.html')


# def Sparql_form_view(request):
#       sent = False
#       form = rdf_queryForm()
#       text=""
#       if request.method == 'POST':
#             form = rdf_queryForm(request.POST)
#             if form.is_valid():
#                   sent=True
#                   text = form.cleaned_data["sparql_query"]
#                   #awaiting the creation of sparql queryfunctions
#       return render(request, 'templates/triples/sparqlpage.html', {"sparqlform": form ,'query': text, 'sent': sent})


def render_main(r):
      return render(r, "static/rdf/main.ttl", content_type='text/turtle')
      