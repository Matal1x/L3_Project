from django.shortcuts import render
from rest_framework.decorators import api_view, permission_classes, authentication_classes
from rest_framework.permissions import IsAuthenticated, IsAuthenticatedOrReadOnly, IsAdminUser
from rest_framework.authentication import SessionAuthentication, BasicAuthentication, TokenAuthentication
from rest_framework.response import Response
from GraphFunc import gfunc
from triples import models
# Create your views here.

@api_view(['GET'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticatedOrReadOnly])
def api_getrdf(request):
    subject = request.GET["subject"]
    predicat = request.GET["predicat"]
    objectV = request.GET["objectV"]
    print(type(objectV))
    rdf_result=gfunc.get_graph(subject,predicat,objectV)
    print( f"{subject} {predicat} {objectV} \n{rdf_result}")
    return Response({"subject": subject, "predicat": predicat, "objectV": objectV, "rdf_result": rdf_result})


@api_view(['GET'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticatedOrReadOnly])
def api_get_rdf(request, subject, predicat, objectV, *args, **kwargs):
    
    #Checking if triples exist
    if not gfunc.graph_exist(subject,predicat,objectV):
        return Response("no matchng triple", status=404)
    
    
    rdf_result=gfunc.get_textual_rdf(subject,predicat,objectV)
    print( f"{subject} {predicat} {objectV} \n{rdf_result}")
    return Response({"subject": subject, "predicat": predicat, "objectV": objectV, "rdf_result": rdf_result})






@api_view(['Delete'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticated, IsAdminUser])
def api_del_triples(request, subject, predicat, objectV, *args, **kwargs):
    if not gfunc.graph_exist(subject,predicat,objectV):
        return Response("no matchng triple", status=404)
    
    gfunc.delete_graph(subject,predicat,objectV)
    return Response("Triple(s) Deleted Successfully")






@api_view(['POST'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticated, IsAdminUser])
def api_add_triple(request, subject, predicat, objectV, *args, **kwargs):
    # print([x[1] for x in models.Triple.Predicat.choices[1:]])
    # return Response("test")
    
    if predicat not in [x[1] for x in models.Triple.Predicat.choices[1:]]:
        print("no such predicat exists")
        return Response("no such predicat exists", status=400)
    
    subject = gfunc.validator(subject); predicat = gfunc.validator(predicat); objectV=gfunc.object_validator(objectV)
    if (subject is None) or (predicat is None) or (objectV is None):
        print("Must set values for all elements of the triple")
        return Response("Must set values for all elements of the triple", status=400)
    
    
    gfunc.add_triple(subject,predicat,objectV)
    return Response("Triple added successfully")







@api_view(['PUT'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticated, IsAdminUser])
def api_update_triple(request, subject, predicat, objectV, *args, **kwargs):
    if not gfunc.graph_exist(subject, predicat, None):
        print("Cannot modify what doesn't exist")
        return Response("Cannot modify what doesn't exist", status=400)
    
    gfunc.modify_triple(subject, predicat, objectV)
    return Response("Modification successfull")
    
    

@api_view(['POST'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticated, IsAdminUser])
def api_add_graph(request):
    try:
        rdf_text=request.data["rdf_text"]
        rdf_format=request.data["rdf_format"]
    except Exception as e:
        return Response(e, status=400)
    else:
        try:
            gfunc.add_graph(rdf_text, rdf_format)
        except:
            print("Bad syntax/format")
        else:
            return Response("Graph added successfully")