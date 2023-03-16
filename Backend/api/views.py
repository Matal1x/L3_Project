from django.shortcuts import render
from rest_framework.decorators import api_view
from rest_framework.response import Response
from GraphFunc import gfunc
# Create your views here.

@api_view(['GET'])
def api_getrdf(request):
    my_data = request.GET
    subject = request.GET["subject"]
    predicat = request.GET["predicat"]
    objectV = request.GET["objectV"]
    print(type(objectV))
    rdf_result=gfunc.get_graph(subject,predicat,objectV)
    print( f"{subject} {predicat} {objectV} \n{rdf_result}")
    return Response({"subject": subject, "predicat": predicat, "objectV": objectV, "rdf_result": rdf_result})


@api_view(['GET'])
def api_get_rdf(request, subject, predicat, objectV, *args, **kwargs):
    
    #Checking if triples exist
    if not gfunc.graph_exist(subject,predicat,objectV):
        return Response("no matchng triple", status=404)
    
    
    rdf_result=gfunc.get_graph(subject,predicat,objectV)
    print( f"{subject} {predicat} {objectV} \n{rdf_result}")
    return Response({"subject": subject, "predicat": predicat, "objectV": objectV, "rdf_result": rdf_result})


@api_view(['GET', 'Delete'])
def api_del_triples(request, subject, predicat, objectV, *args, **kwargs):
    if not gfunc.graph_exist(subject,predicat,objectV):
        return Response("no matchng triple", status=404)
    
    gfunc.delete_graph(subject,predicat,objectV)
    return Response("Triple(s) Deleted Successfully")