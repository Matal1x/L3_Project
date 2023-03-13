from django import forms
from triples.models import  Triple


class MyForm(forms.ModelForm):
    class Meta: #going to connect the model to the form fields
        model=Triple
        fields= "__all__"
form=MyForm
