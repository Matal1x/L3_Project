# Generated by Django 4.1.7 on 2023-03-30 13:22

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('triples', '0003_rename_triple_dohtriple_delete_rdf_query'),
    ]

    operations = [
        migrations.CreateModel(
            name='KDDTriple',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('subject', models.CharField(default=None, max_length=255, null=True)),
                ('predicat', models.CharField(choices=[('None', 'All'), ('duration', 'Duration'), ('protocol_type', 'Protocol Type'), ('service', 'Service'), ('flag', 'flag'), ('src_bytes', 'Src Bytes'), ('dst_bytes', 'Dst bytes'), ('count', 'Count'), ('srv_count', 'Srv Count'), ('dst_host_count', 'dst_host_count'), ('dst_host_srv_count', 'Dst Host Srv Count'), ('dst_host_same_src_port_rate', 'Dst Host Same Src Port Rate'), ('dst_host_srv_diff_host_rate', 'Dst Host Srv Diff Host Rate'), ('dst_host_srv_rerror_rate', 'Dst Host Srv Rerror Rate'), ('class', 'Class')], default=None, max_length=255, null=True)),
                ('objectV', models.CharField(default=None, max_length=255, null=True)),
            ],
        ),
    ]
