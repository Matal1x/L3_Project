from django.shortcuts import render
from . import models
from . import forms 
# Create your views here.

def index(request):
    return render(request,'triples/index.html')

def form_name_view(request):
    form=forms.MyForm() #instance of a form
    if request.method == 'POST':
        form= forms.MyForm(request.POST)
        if form.is_valid():
            form.save()
            print("validated data")
            print("subject:"+form.cleaned_data['subject'])
            print("predicate:"+form.cleaned_data['predicate'])
            print("obj:"+form.cleaned_data['obj'])
    return render(request,'triples/form_page.html',{'form':form}) #request,pass in th page i want to show,pass in the context dictionary: we'll give it the key form and we'll pass in that actual form object 
