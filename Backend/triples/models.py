from django.db import models

# Create your models here.

class Triple(models.Model):
    Subject = models.CharField(max_length=255)