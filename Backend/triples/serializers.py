from rest_framework import serializers
from . models import Triple

class TripleSerializer(serializers.ModelSerializer):
    class Meta:
        model=Triple
        fields=[
            'subject',
            'predicate',
            'object'
        ]