from django.urls import path

from . import views 

urlpatterns=[
    path('',views.form_name_view),
    path('request/', views.Sparql_form_view, name="sparql_get_request"),
    
]
