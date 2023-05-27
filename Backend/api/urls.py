from django.urls import path, re_path
from . import views
urlpatterns= [

    #path('get/SELECT/<slug:graph>/<str:subject>/<str:predicat>/<slug:objectV>/', views.api_get_SELECT_JSON, name='SELECT'),
    re_path(r'get/SELECT/(?P<graph>[\w-]+)/(?P<subject>[a-zA-Z0-9]+)/(?P<predicat>-?\d+(\.\d+)?|\w+(\.\w+)*)/(?P<objectV>-?\d+(\.\d+)?|\w+(\.\w+)*)/$', views.api_get_SELECT_JSON, name='SELECT_re'),
    
    #path('get/ASK/<slug:graph>/<str:subject>/<str:predicat>/<slug:objectV>/', views.api_get_ASK, name='ASK'),
    re_path(r'get/ASK/(?P<graph>[\w-]+)/(?P<subject>[a-zA-Z0-9]+)/(?P<predicat>-?\d+(\.\d+)?|\w+(\.\w+)*)/(?P<objectV>-?\d+(\.\d+)?|\w+(\.\w+)*)/$', views.api_get_ASK, name='ASK_re'),
    
    #path('get/CONSTRUCT/<slug:graph>/<str:subject>/<str:predicat>/<slug:objectV>/', views.api_get_CONSTRUCT, name='CONSTRUCT'),
    re_path(r'get/CONSTRUCT/(?P<graph>[\w-]+)/(?P<subject>[a-zA-Z0-9]+)/(?P<predicat>-?\d+(\.\d+)?|\w+(\.\w+)*)/(?P<objectV>-?\d+(\.\d+)?|\w+(\.\w+)*)/$', views.api_get_CONSTRUCT, name='CONSTRUCT_re'),
    
    
    
    
    
    
    #path('put/INSERT/<slug:graph>/<str:subject>/<str:predicat>/<slug:objectV>/', views.api_put_INSERT, name='INSERT'),
    re_path(r'put/INSERT/(?P<graph>[\w-]+)/(?P<subject>[a-zA-Z0-9]+)/(?P<predicat>-?\d+(\.\d+)?|\w+(\.\w+)*)/(?P<objectV>-?\d+(\.\d+)?|\w+(\.\w+)*)/$', views.api_put_INSERT, name='INSERT_re'),
    
    #path('put/DELETE/<slug:graph>/<str:subject>/<str:predicat>/<slug:objectV>/', views.api_put_DELETE, name='DELETE'), 
    re_path(r'put/DELETE/(?P<graph>[\w-]+)/(?P<subject>[a-zA-Z0-9]+)/(?P<predicat>-?\d+(\.\d+)?|\w+(\.\w+)*)/(?P<objectV>-?\d+(\.\d+)?|\w+(\.\w+)*)/$', views.api_put_DELETE, name='DELETE_re'),
    
    #path('put/MODIFY/<slug:graph>/<str:subject>/<str:predicat>/<slug:objectV>/', views.api_put_MODIFY, name='MODIFY'),
    re_path(r'put/MODIFY/(?P<graph>[\w-]+)/(?P<subject>[a-zA-Z0-9]+)/(?P<predicat>-?\d+(\.\d+)?|\w+(\.\w+)*)/(?P<objectV>-?\d+(\.\d+)?|\w+(\.\w+)*)/$', views.api_put_MODIFY, name='MODIFY_re'),
]