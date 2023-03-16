from django.urls import path
from . import views
urlpatterns= [
    path('get/', views.api_getrdf, name="getrdf"),
    path('get/<str:subject>/<str:predicat>/<str:objectV>/', views.api_get_rdf,name='get_rdf'),
    path('delete/<str:subject>/<str:predicat>/<str:objectV>/', views.api_del_triples,name='delete_triples'),
    
]