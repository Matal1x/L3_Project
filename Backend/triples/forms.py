from django import forms
from . import  models

 
class DoHTripleForm(forms.ModelForm):
    subject=forms.CharField(max_length=255,required=False,widget=forms.TextInput(attrs={"placeholder":"Attack N° (optional)"}))
    objectV=forms.CharField(max_length=255,required=False,widget=forms.TextInput(attrs={"placeholder":"Value (optional)"}))
    
    class Meta: #going to connect the model to the form fields
        model=models.DoHTriple
        fields= [
            'subject',
            'predicat',
            'objectV',
        ]
class KDDTripleForm(forms.ModelForm):
    subject=forms.CharField(max_length=255,required=False,widget=forms.TextInput(attrs={"placeholder":"Attack N° (optional)"}))
    objectV=forms.CharField(max_length=255,required=False,widget=forms.TextInput(attrs={"placeholder":"Value (optional)"}))
    
    class Meta: #going to connect the model to the form fields
        model=models.KDDTriple
        fields= [
            'subject',
            'predicat',
            'objectV',
        ]


        