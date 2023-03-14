from django.shortcuts import render
from .models import Triple
from .forms import TripleForm
# Create your views here.




def index(request):
        return render(request,'templates/generatedgraphs/index.html')

def form_name_view(request):
        form=TripleForm() #instance of a form
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
                print("subject:", form.cleaned_data['subject'])
                print("predicate:", form.cleaned_data['predicat'])
                print("objectV:", form.cleaned_data['objectV'])


        return render(request,'templates/generatedgraphs/formpage.html',{'form':form}) #request,pass in th page i want to show,pass in the context dictionary: we'll give it the key form and we'll pass in that actual form object 

