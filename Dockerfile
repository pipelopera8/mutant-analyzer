#FROM flypass/builder:1
FROM store/oracle/serverjre:8

#Ruta del jar
ADD mutant-analyzer-service/target/MutanAnalyzerService.jar app.jar

#Indicamos los puertos a exponer
EXPOSE 7220 

#Se agrega start.sh
ADD mutant-analyzer-service/start.sh /opt
RUN chmod +x /opt/start.sh
#Indicamos que siempre ejecute el .sh
CMD ["/opt/start.sh"]