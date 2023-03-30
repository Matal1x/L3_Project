from django.urls import path
from . import views
urlpatterns= [

    path('get/SELECT/<slug:graph>/<str:subject>/<str:predicat>/<str:objectV>/', views.api_get_SELECT_JSON, name='SELECT'),
    path('get/ASK/<slug:graph>/<str:subject>/<str:predicat>/<str:objectV>/', views.api_get_ASK, name='ASK'),
    path('get/CONSTRUCT/<slug:graph>/<str:subject>/<str:predicat>/<str:objectV>/', views.api_get_CONSTRUCT, name='CONSTRUCT'),
    
    path('put/INSERT/<slug:graph>/<str:subject>/<str:predicat>/<str:objectV>/', views.api_put_INSERT, name='INSERT'),
    path('put/DELETE/<slug:graph>/<str:subject>/<str:predicat>/<str:objectV>/', views.api_put_DELETE, name='DELETE'), 
    path('put/MODIFY/<slug:graph>/<str:subject>/<str:predicat>/<str:objectV>/', views.api_put_MODIFY, name='MODIFY'),
]