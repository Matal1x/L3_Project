from django import forms
from . import  models

 
class DoHTripleForm(forms.ModelForm):
    subject=forms.CharField(max_length=255,required=False,widget=forms.TextInput(attrs={"placeholder":"All (or enter the number of the Attack)"}))
    class Meta: #going to connect the model to the form fields
        model=models.DoHTriple
        fields= [
            'subject',
            'predicat',
        ]
        
class KDDTripleForm(forms.ModelForm):
    subject=forms.CharField(max_length=255,required=False,widget=forms.TextInput(attrs={"placeholder":"All (or enter the number of the Attack)"}))
    class Meta: #going to connect the model to the form fields
        model=models.KDDTriple
        fields= [
            'subject',
            'predicat',
        ]