from django import forms
from . import  models

 
class TripleForm(forms.ModelForm):
    subject=forms.CharField(max_length=255,required=False,widget=forms.TextInput(attrs={"placeholder":"All"}))
    objectV=forms.CharField(max_length=255,required=False,widget=forms.TextInput(attrs={"placeholder":"All"}))
    class Meta: #going to connect the model to the form fields
        model=models.Triple
        fields= [
            'subject',
            'predicat',
            'objectV',
        ]
#form=TripleForm

class rdf_queryForm(forms.ModelForm):
    sparql_query=forms.CharField(max_length=1000, required=True, widget=forms.Textarea(attrs={"placeholder": "Pass in your SPARQL query"}))
    class Meta:
        model=models.rdf_query
        fields = ['sparql_query']