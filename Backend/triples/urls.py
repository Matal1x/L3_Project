from django.urls import path

from . import views 

app_name="triples"

urlpatterns=[
    path('DoHform/', views.DoHform, name="DoHform"),
    path("DoHgraph/", views.DoHgraph, name="DoHgraph"),
    
    path("KDDform/", views.KDDform ,name="KDDform"),
    path("KDDgraph/", views.KDDgraph, name="KDDgraph"),
]
