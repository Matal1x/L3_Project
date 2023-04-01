from django.urls import path

from . import views 

app_name="triples"

urlpatterns=[
    path("form/<slug:dataset>/", views.Myform ,name="form"),
    path("graph/", views.graph, name="graph"),
]
