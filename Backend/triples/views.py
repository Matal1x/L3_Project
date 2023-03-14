from django.shortcuts import render
from .models import Triple
from .forms import TripleForm
from GraphFunc import gfunc
# Create your views here.




def index(request):
        return render(request,'templates/triples/index.html')

def form_name_view(request):
        form=TripleForm() #instance of a form
        checked = False
        if request.method == 'POST':
            form= TripleForm(request.POST)
            if form.is_valid():
                print("validated data")
                if form.cleaned_data['subject'] == "":
                      form.cleaned_data['subject']=None
                if form.cleaned_data['predicat'] == "None":
                      form.cleaned_data['predicat']=None
                if form.cleaned_data['objectV'] == "":
                      form.cleaned_data['objectV']=None
                
                gfunc.get_graph(form.cleaned_data['subject'], form.cleaned_data['predicat'], form.cleaned_data['objectV'])
                checked = True


        return render(request,'templates/triples/formpage.html',{'form':form, 'checked': checked}) #request,pass in th page i want to show,pass in the context dictionary: we'll give it the key form and we'll pass in that actual form object 

