package com.taxis.business;

import com.taxis.entities.describer.Endpoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by marcosarruda on 2/15/16.
 */
public class EndpointFactory {
    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String DELETE = "DELETE";
    public static List<Endpoint> buildEndpoints(){

        List<Endpoint> endpoints = new ArrayList<Endpoint>();

        Endpoint criaTaxista = createEndPoint("http://marcosarruda.info:8080/taxis/drivers", POST,
                "Este endpoint cria/insere um novo Taxi Driver; Você receberá 200 HTTP como resposta se tudo andar corretamente","200 HTTP");
        criaTaxista.setRequestParams(createRequestParams("name", "carPlate"));

        Endpoint criaTaxistaRet = createEndPoint("http://marcosarruda.info:8080/taxis/drivers/ret", POST,
                "Este endpoint cria/insere um novo Taxi Driver; Você receberá 200 HTTP juntamente com o Id do novo taxista criado","200 HTTP {'driverId':'asd34sxvs'}");
        criaTaxistaRet.setRequestParams(createRequestParams("name", "carPlate"));

        Endpoint getDriverIdPorNome = createEndPoint("http://marcosarruda.info:8080/taxis/drivers/name/{strname}", GET,
                "Este endpoint retorna o driverId de um Taxi Driver efetuando a busca pelo nome do taxista; Você receberá 200 HTTP com DriverId se tudo correr bem","200 HTTP {'driverId':'asd34sxvs'}");
        getDriverIdPorNome.setRequestParams(createRequestParams("{strname} - na url"));

        Endpoint getDriverIdPorCarPlate = createEndPoint("http://marcosarruda.info:8080/taxis/drivers/carplate/{strcarplate}", GET,
                "Este endpoint Atualiza o status de um Taxi Driver identificado pelo {driverId}; Você receberá 200 HTTP se tudo correr bem","200 HTTP");
        getDriverIdPorCarPlate.setRequestParams(createRequestParams("{strcarplate} - na url"));

        Endpoint deleteTaxista = createEndPoint("http://marcosarruda.info:8080/taxis/drivers/{driverId}", DELETE,
                "Este endpoint remove/deleta um Taxi Driver identificado pelo {driverId}; Você receberá 200 HTTP se tudo correr bem","200 HTTP");
        deleteTaxista.setRequestParams(createRequestParams("driverId"));

        Endpoint updateStatusTaxista = createEndPoint("http://marcosarruda.info:8080/taxis/drivers/{driverId}/status", POST,
                "Este endpoint Atualiza o status de um Taxi Driver identificado pelo {driverId}; Você receberá 200 HTTP se tudo correr bem","200 HTTP");
        updateStatusTaxista.setRequestParams(createRequestParams("{driverId} - na url", "driverId", "latitude", "longitude", "driverAvailable"));

        Endpoint getStatusTaxista = createEndPoint("http://marcosarruda.info:8080/taxis/drivers/{driverId}/status", GET,
                "Este endpoint retorna o status atual de um Taxi Driver identificado pelo {driverId}; Você receberá o status do taxista(lat, long, available) juntamente com o 200 HTTP se tudo correr bem","{'driverId':'dfgr55fv6', 'latitude':'23.567', 'longitude':'46.7684', 'driverAvailable':'true/false'}");
        getStatusTaxista.setRequestParams(createRequestParams("{driverId} - na url"));

        Endpoint findStatusTaxistaInArea = createEndPoint("http://marcosarruda.info:8080/taxis/drivers/inArea?sw=-23.612474,-46.702746&ne=-23.589548,-46.673392", GET,
                "Este endpoint retorna uma lista de status de Taxi Drivers dentro de um Retangulo definido pelos pontos:'sw(south-west) e ne(north-east).; A busca implementada considera os limites como inclusivos'; Você receberá a lista com o status de todos os taxistas(lat, long, available) que se estão atualmente dentro do retangulo, juntamente com o 200 HTTP se tudo correr bem","[{'driverId':'dfgr55fv6', 'latitude':'23.567', 'longitude':'46.7684', 'driverAvailable':'true/false'}, {'driverId':'df2r532v4', 'latitude':'22.53467', 'longitude':'46.2145', 'driverAvailable':'true/false'}]");
        findStatusTaxistaInArea.setRequestParams(createRequestParams("sw=-LAT,LNG", "ne=-LAT,LNG"));

        endpoints.add(criaTaxista);
        endpoints.add(criaTaxistaRet);
        endpoints.add(getDriverIdPorNome);
        endpoints.add(getDriverIdPorCarPlate);
        endpoints.add(deleteTaxista);
        endpoints.add(updateStatusTaxista);
        endpoints.add(getStatusTaxista);
        endpoints.add(findStatusTaxistaInArea);


        return endpoints;
    }

    private static Endpoint createEndPoint(String link, String method, String description, String responseExample){
        //Endpoint p = new Endpoint("POST http://marcosarruda.info/taxis/drivers",
         //       "This endpoint creates/registers a new Taxi Driver. You will get 200 HTTP response if everything went fine.");
        Endpoint p = new Endpoint(link, method, description);
        p.setResponseExample(responseExample);
        return p;
    }

    private static final List<String> createRequestParams(String... args){
        return Arrays.asList(args);
    }
}
