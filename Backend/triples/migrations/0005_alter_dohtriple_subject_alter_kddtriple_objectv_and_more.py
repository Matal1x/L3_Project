# Generated by Django 4.1.7 on 2023-05-15 17:22

import django.core.validators
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('triples', '0004_kddtriple'),
    ]

    operations = [
        migrations.AlterField(
            model_name='dohtriple',
            name='subject',
            field=models.IntegerField(default=0, max_length=255, null=True, validators=[django.core.validators.MinValueValidator(0)]),
        ),
        migrations.AlterField(
            model_name='kddtriple',
            name='objectV',
            field=models.CharField(default=0, max_length=255, null=True),
        ),
        migrations.AlterField(
            model_name='kddtriple',
            name='subject',
            field=models.IntegerField(default=0, max_length=255, null=True, validators=[django.core.validators.MinValueValidator(0)]),
        ),
    ]
