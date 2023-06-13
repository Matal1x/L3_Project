from django.db import models
from django.utils.translation import gettext_lazy as _
from django.core.validators import MaxValueValidator, MinValueValidator
# Create your models here.

class DoHTriple(models.Model):
    class Predicat(models.TextChoices):
        All = None, _("All")
        SourceIP = 'SourceIP', _('Source IP') 
        DestinationIP= 'DestinationIP', _('Destination IP')
        SourcePort= 'SourcePort', _('SourcePort')
        DestinationPort= 'DestinationPort', _('Destination Port')
        TimeStamp= 'TimeStamp', _('Time Stamp')
        Duration= 'Duration', _('Duration')
        FlowBytesSent= 'FlowBytesSent', _('Flow Bytes Sent')
        FlowSentRate= 'FlowSentRate', _('Flow Sent Rate')
        FlowBytesReceived= 'FlowBytesReceived', _('Flow Bytes Received')
        FlowReceivedRate= 'FlowReceivedRate', _('Flow Received Rate')
        PacketLengthVariance= 'PacketLengthVariance', _('Packet Length Variance')
        PacketLengthStandardDeviation= 'PacketLengthStandardDeviation', _('Packet Length Standard Deviation')
        PacketLengthMean = 'PacketLengthMean', _('Packet Length Mean')
        PacketLengthMedian = 'PacketLengthMedian', _('Packet Length Median')
        PacketLengthMode= 'PacketLengthMode', _('Packet Length Mode')
        PacketLengthSkewFromMedian= 'PacketLengthSkewFromMedian', _('Packet Length Skew From Median')
        PacketLengthSkewFromMode = 'PacketLengthSkewFromMode', _('Packet Length Skew From Mode')
        PacketLengthCoefficientofVariation = 'PacketLengthCoefficientofVariation', _('Packet Length Coefficient of Variation')
        PacketTimeVariance = 'PacketTimeVariance', _('Packet Time Variance')
        PacketTimeStandardDeviation= 'PacketTimeStandardDeviation', _('Packet Time Standard Deviation')
        PacketTimeMean= 'PacketTimeMean', _('Packet TimeMean')
        PacketTimeMedian= 'PacketTimeMedian', _('Packet Time Median')
        PacketTimeMode = 'PacketTimeMode', _('Packet Time Mode')
        PacketTimeSkewFromMedian= 'PacketTimeSkewFromMedian', _('Packet Time Skew From Median')
        PacketTimeSkewFromMode= 'PacketTimeSkewFromMode', _('Packet Time Skew From Mode')
        PacketTimeCoefficientofVariation= 'PacketTimeCoefficientofVariation', _('Packet Time Coefficient of Variation')
        ResponseTimeTimeVariance= 'ResponseTimeTimeVariance', _('Response Time Time Variance')
        ResponseTimeTimeStandardDeviation= 'ResponseTimeTimeStandardDeviation', _('Response Time Time Standard Deviation')
        ResponseTimeTimeMean= 'ResponseTimeTimeMean', _('Response Time Time Mean')
        ResponseTimeTimeMedian= 'ResponseTimeTimeMedian', _('Response Time TimeMedian')
        ResponseTimeTimeMode= 'ResponseTimeTimeMode', _('Response Time Time Mode')
        ResponseTimeTimeSkewFromMedian= 'ResponseTimeTimeSkewFromMedian', _('Response Time Time Skew From Median')
        ResponseTimeTimeSkewFromMode= 'ResponseTimeTimeSkewFromMode', _('Response Time Time Skew From Mode')
        ResponseTimeTimeCoefficientofVariation= 'ResponseTimeTimeCoefficientofVariation', _('Response Time Time Coefficient of Variation')
        DoH = 'DoH', _('DoH')
    subject = models.IntegerField(default=0,null=True, blank=False,validators = [MinValueValidator(0)])
    predicat = models.CharField(max_length=255, choices=Predicat.choices, default=None, null=True)
    objectV = models.CharField(max_length=255, default=None,null=True,blank=False)
    
    def __str__(self) -> str:
        return f"{self.subject}, {self.predicat}, {self.objectV}"
    
    
class KDDTriple(models.Model):
    class Predicat(models.TextChoices):
        All = None, _("All")
        duration='duration', _('Duration')
        protocol_type= 'protocol_type', _('Protocol Type')
        service= 'service', _('Service')
        flag= 'flag', _('flag')
        src_bytes= 'src_bytes', _("Src Bytes")
        dst_bytes= 'dst_bytes', _('Dst bytes')
        count= 'count', _('Count')
        srv_count= 'srv_count', _('Srv Count')
        dst_host_count= 'dst_host_count', _("dst_host_count")
        dst_host_srv_count= 'dst_host_srv_count', _('Dst Host Srv Count')
        dst_host_same_src_port_rate= 'dst_host_same_src_port_rate', _('Dst Host Same Src Port Rate')
        dst_host_srv_diff_host_rate= 'dst_host_srv_diff_host_rate', _('Dst Host Srv Diff Host Rate')
        dst_host_srv_rerror_rate= 'dst_host_srv_rerror_rate', _('Dst Host Srv Rerror Rate')
        CLASS = 'class', _('Class')
    
    subject = models.IntegerField(default=0,null=True, blank=False,validators = [MinValueValidator(0)])
    predicat = models.CharField(max_length=255, choices=Predicat.choices, default=None, null=True)
    objectV = models.CharField(max_length=255, default=0,null=True,blank=False)
