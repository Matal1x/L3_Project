from rest_framework import serializers
from . models import DoHTriple

class TripleSerializer(serializers.ModelSerializer):
    class Meta:
        model=DoHTriple
        fields=[
            'subject',
            'predicate',
            'object'
        ]