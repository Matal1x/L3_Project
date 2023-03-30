from django.shortcuts import render
from rest_framework.decorators import api_view, permission_classes, authentication_classes
from rest_framework.permissions import IsAuthenticated, IsAuthenticatedOrReadOnly, IsAdminUser
from rest_framework.authentication import SessionAuthentication, BasicAuthentication, TokenAuthentication
from rest_framework.response import Response
from triples import models
from . import PY4J_customf
# Create your views here.



@api_view(['GET'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticatedOrReadOnly])
def api_get_SELECT_JSON(request, graph, subject, predicat, objectV, *args, **kwargs):
    
    subject=PY4J_customf.None_affirmer(subject); predicat=PY4J_customf.None_affirmer(predicat); objectV=PY4J_customf.None_affirmer(objectV)
    (subject, predicat, objectV)= PY4J_customf.triple_adapter(subject, predicat, objectV)
    if not PY4J_customf.ASK_QUERY(graph, subject, predicat, objectV):
        return Response("no result found.", status=404)
    
    result = PY4J_customf.SELECT_QUERY_JS(graph, subject, predicat, objectV)
    return Response(result)
    






    
@api_view(['GET'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticatedOrReadOnly])    
def api_get_ASK(request, graph, subject, predicat, objectV, *args, **kwargs):
    
    subject=PY4J_customf.None_affirmer(subject); predicat=PY4J_customf.None_affirmer(predicat); objectV=PY4J_customf.None_affirmer(objectV)
    (subject, predicat, objectV)= PY4J_customf.triple_adapter(subject, predicat, objectV)
    
    result = PY4J_customf.ASK_QUERY(graph, subject, predicat, objectV)
    return Response(f"ASk QUERY result: {result}")
    






@api_view(['GET'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticatedOrReadOnly])
def api_get_CONSTRUCT(request, graph, subject, predicat, objectV, *args, **kwargs):
    
    subject=PY4J_customf.None_affirmer(subject); predicat=PY4J_customf.None_affirmer(predicat); objectV=PY4J_customf.None_affirmer(objectV)
    (subject, predicat, objectV)= PY4J_customf.triple_adapter(subject, predicat, objectV)
    print(type(objectV), "    " , objectV)
    
    if not PY4J_customf.ASK_QUERY(graph, subject, predicat, objectV):
        return Response("no result found.", status=404)
    
    result = PY4J_customf.CONSTRUCT_QUERY(graph, subject, predicat, objectV)
    return Response(result)
    





@api_view(['POST'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticated, IsAdminUser])
def api_put_INSERT(request, graph, subject, predicat, objectV, *args, **kwargs):
    
    subject=PY4J_customf.None_affirmer(subject); predicat=PY4J_customf.None_affirmer(predicat); objectV=PY4J_customf.None_affirmer(objectV)
    if predicat not in [x[1] for x in models.Triple.Predicat.choices[1:]]:
        print("no such predicat exists")
        return Response("no such predicat exists", status=400)
    
    (subject, predicat, objectV)= PY4J_customf.triple_adapter(subject, predicat, objectV)
    if (subject is None) or (predicat is None) or (objectV is None):
        print("Must set values for all elements of the triple")
        return Response("Must set values for all elements of the triple", status=400)
    
    result = PY4J_customf.INSERT_QUERY(graph, subject, predicat, objectV)
    return Response("Triple added successfuly.")





@api_view(['Delete'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticated, IsAdminUser])
def api_put_DELETE(request, graph, subject, predicat, objectV, *args, **kwargs):
    
    subject=PY4J_customf.None_affirmer(subject); predicat=PY4J_customf.None_affirmer(predicat); objectV=PY4J_customf.None_affirmer(objectV)
    (subject, predicat, objectV)= PY4J_customf.triple_adapter(subject, predicat, objectV)
    if not PY4J_customf.ASK_QUERY(graph, subject, predicat, objectV):
        return Response("No matching triple. Cannot delete what doesn't exist.", status=404)
    
     
    result = PY4J_customf.DELETE_QUERY(graph, subject, predicat, objectV)
    return Response("Triple(s) Deleted Successfully")
    






@api_view(['PUT'])
@authentication_classes([TokenAuthentication])
@permission_classes([IsAuthenticated, IsAdminUser])
def api_put_MODIFY(request, graph, subject, predicat, objectV, *args, **kwargs):
    
    subject=PY4J_customf.None_affirmer(subject); predicat=PY4J_customf.None_affirmer(predicat); objectV=PY4J_customf.None_affirmer(objectV)
    (subject, predicat, objectV)= PY4J_customf.triple_adapter(subject, predicat, None)
    if not PY4J_customf.ASK_QUERY(graph, subject, predicat, objectV):
        return Response("Cannot Modify what doesn't exist.", status=404)
    
    result = PY4J_customf.DELETE_INSERT_QUERY(graph, subject, predicat, objectV)
    return Response("Modification successfull")
    
    

# @api_view(['POST'])
# @authentication_classes([TokenAuthentication])
# @permission_classes([IsAuthenticated, IsAdminUser])
# def api_add_graph(request):
#     try:
#         rdf_text=request.data["rdf_text"]
#         rdf_format=request.data["rdf_format"]
#     except Exception as e:
#         return Response(e, status=400)
#     else:
#         try:
#             gfunc.add_graph(rdf_text, rdf_format)
#         except:
#             print("Bad syntax/format")
#         else:
#             return Response("Graph added successfully")