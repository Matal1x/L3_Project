from django.shortcuts import render
from .models import Triple
from .forms import TripleForm
from GraphFunc import gfunc
from django.views.generic import View,TemplateView
# Create your views here.

class MainGraphView(TemplateView):
      template_name='generatedgraphs/main.ttl'


def index(request):
        return render(request,'templates/triples/index.html')

def form_name_view(request):
        form=TripleForm() #instance of a form
        checked = False
        graph=gfunc.get_graph()
        if request.method == 'POST':
            form= TripleForm(request.POST)
            graph=gfunc.get_graph()
            if form.is_valid():
                print("validated data")
                if form.cleaned_data['subject'] == "":
                      form.cleaned_data['subject']=None
                if form.cleaned_data['predicat'] == "None":
                      form.cleaned_data['predicat']=None
                if form.cleaned_data['objectV'] == "":
                      form.cleaned_data['objectV']=None
                print("subject:", form.cleaned_data['subject'])
                print("predicate:", form.cleaned_data['predicat'])
                print("objectV:", form.cleaned_data['objectV'])
                
                gfunc.get_graph(form.cleaned_data['subject'], form.cleaned_data['predicat'], form.cleaned_data['objectV'])
               
                checked = True 
                
            
            

        return render(request,'templates/triples/formpage.html',{'form':form, 'checked': checked,'graph':graph }) #request,pass in th page i want to show,pass in the context dictionary: we'll give it the key form and we'll pass in that actual form object 


