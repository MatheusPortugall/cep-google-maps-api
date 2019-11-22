package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;



@CronapiMetaData(type = "blockly")
@CronappSecurity
public class PegaTexto {

public static final int TIMEOUT = 300;

/**
 *
 * @return Var
 */
// pegaTexto
public static Var pegaTextoInput() throws Exception {
 return new Callable<Var>() {

   private Var valorSelecionado = Var.VAR_NULL;

   public Var call() throws Exception {
    valorSelecionado = cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.combobox6705"));
    if (Var.valueOf(valorSelecionado.equals(Var.valueOf(2))).getObjectAsBoolean()) {
        cronapi.util.Operations.callClientFunction(Var.valueOf("blockly.js.blockly.Localizacao.obter_coordenadas"));
    }
    return Var.VAR_NULL;
   }
 }.call();
}

/**
 */
// Descreva esta função...
public static void buscaCep() throws Exception {
  new Callable<Var>() {

   private Var valorSelecionado = Var.VAR_NULL;
   private Var cep = Var.VAR_NULL;
   private Var urlCep = Var.VAR_NULL;
   private Var consultaEndereco = Var.VAR_NULL;
   private Var via_cep_cidade = Var.VAR_NULL;
   private Var via_cep_estado = Var.VAR_NULL;
   private Var via_cep_bairro = Var.VAR_NULL;
   private Var via_cep_logradouro = Var.VAR_NULL;

   public Var call() throws Exception {
    valorSelecionado = cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.combobox6705"));
    if (Var.valueOf(valorSelecionado.equals(Var.valueOf(3))).getObjectAsBoolean()) {
        cep = cronapi.screen.Operations.getValueOfField(Var.valueOf("vars.cep"));
        urlCep = Var.valueOf(Var.valueOf("https://viacep.com.br/ws/").toString() + cep.toString() + Var.valueOf("/json/unicode/").toString());
        consultaEndereco = cronapi.json.Operations.toJson(cronapi.util.Operations.getURLFromOthers(Var.valueOf("GET"), Var.valueOf("application/json"), urlCep, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL));
        System.out.println(Var.valueOf("JSON:").getObjectAsString());
        System.out.println(consultaEndereco.getObjectAsString());
        via_cep_cidade = cronapi.json.Operations.getJsonOrMapField(consultaEndereco, Var.valueOf("localidade"));
        via_cep_estado = cronapi.json.Operations.getJsonOrMapField(consultaEndereco, Var.valueOf("uf"));
        via_cep_bairro = cronapi.json.Operations.getJsonOrMapField(consultaEndereco, Var.valueOf("bairro"));
        via_cep_logradouro = cronapi.json.Operations.getJsonOrMapField(consultaEndereco, Var.valueOf("logradouro"));
        cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.cidade"), via_cep_cidade);
        cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.estado"), via_cep_estado);
        cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.bairro"), via_cep_bairro);
        cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.logradouro"), via_cep_logradouro);
        mostrar_endereco_via_cep(consultaEndereco);
    }
   return Var.VAR_NULL;
   }
 }.call();
}

/**
 *
 * @param endereco
 */
// Descreva esta função...
public static void mostrar_endereco_via_cep(Var endereco) throws Exception {
  new Callable<Var>() {

   private Var logradouro = Var.VAR_NULL;
   private Var bairro = Var.VAR_NULL;
   private Var cidade = Var.VAR_NULL;
   private Var exibirCep = Var.VAR_NULL;
   private Var exibirEstado = Var.VAR_NULL;
   private Var pais = Var.VAR_NULL;
   private Var link = Var.VAR_NULL;
   private Var get_via_cep_maps = Var.VAR_NULL;
   private Var results = Var.VAR_NULL;
   private Var geometry = Var.VAR_NULL;
   private Var via_cep_coordernadas = Var.VAR_NULL;
   private Var longitude = Var.VAR_NULL;
   private Var latitude = Var.VAR_NULL;
   private Var latitude_longitude_via_cep = Var.VAR_NULL;
   private Var via_cep_link_maps = Var.VAR_NULL;

   public Var call() throws Exception {
    logradouro = cronapi.json.Operations.getJsonOrMapField(endereco, Var.valueOf("logradouro"));
    bairro = cronapi.json.Operations.getJsonOrMapField(endereco, Var.valueOf("bairro"));
    cidade = cronapi.json.Operations.getJsonOrMapField(endereco, Var.valueOf("localidade"));
    exibirCep = cronapi.json.Operations.getJsonOrMapField(endereco, Var.valueOf("cep"));
    exibirEstado = cronapi.json.Operations.getJsonOrMapField(endereco, Var.valueOf("uf"));
    pais = Var.valueOf("BR");
    System.out.println(Var.valueOf("LINK:").getObjectAsString());
    link = Var.valueOf(Var.valueOf("https://maps.googleapis.com/maps/api/geocode/json?address=").toString() + exibirCep.toString() + Var.valueOf("&key=").toString() + Var.valueOf("AIzaSyA2paKBkuaM15miMJk9wxkdvGz2tzby5y4").toString());
    System.out.println(link.getObjectAsString());
    get_via_cep_maps = cronapi.json.Operations.toJson(cronapi.util.Operations.getURLFromOthers(Var.valueOf("GET"), Var.valueOf("application/json"), link, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL));
    results = cronapi.json.Operations.getJsonOrMapField(get_via_cep_maps, Var.valueOf("results[0]"));
    System.out.println(results.getObjectAsString());
    geometry = cronapi.json.Operations.getJsonOrMapField(results, Var.valueOf("geometry"));
    via_cep_coordernadas = cronapi.json.Operations.getJsonOrMapField(geometry, Var.valueOf("location"));
    longitude = cronapi.json.Operations.getJsonOrMapField(via_cep_coordernadas, Var.valueOf("lng"));
    latitude = cronapi.json.Operations.getJsonOrMapField(via_cep_coordernadas, Var.valueOf("lat"));
    latitude_longitude_via_cep = Var.valueOf(Var.valueOf("latlng=").toString() + latitude.toString() + Var.valueOf(",").toString() + longitude.toString());
    via_cep_link_maps = Var.valueOf(Var.valueOf("https://maps.googleapis.com/maps/api/geocode/json?").toString() + latitude_longitude_via_cep.toString() + Var.valueOf("&key=AIzaSyA2paKBkuaM15miMJk9wxkdvGz2tzby5y4").toString());
    System.out.println(via_cep_link_maps.getObjectAsString());
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.cidade"), cidade);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.estado"), exibirEstado);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.cep"), exibirCep);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.logradouro"), logradouro);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.bairro"), bairro);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeAttrValue"), Var.valueOf("google-maps"), Var.valueOf("src"), via_cep_link_maps);
    cronapi.util.Operations.callClientFunction(Var.valueOf("blockly.js.blockly.Localizacao.definir_rendodeza_via_cep"),latitude, longitude);
   return Var.VAR_NULL;
   }
 }.call();
}

/**
 *
 * @param coordenadas
 */
// Descreva esta função...
public static void obter_endereco(Var coordenadas) throws Exception {
  new Callable<Var>() {

   private Var consultaEndereco = Var.VAR_NULL;
   private Var endereco = Var.VAR_NULL;
   private Var results = Var.VAR_NULL;
   private Var url = Var.VAR_NULL;

   public Var call() throws Exception {
    url = Var.valueOf(Var.valueOf("https://maps.googleapis.com/maps/api/geocode/json?").toString() + coordenadas.toString() + Var.valueOf("&key=AIzaSyA2paKBkuaM15miMJk9wxkdvGz2tzby5y4").toString());
    System.out.println(url.getObjectAsString());
    consultaEndereco = cronapi.json.Operations.toJson(cronapi.util.Operations.getURLFromOthers(Var.valueOf("GET"), Var.valueOf("application/json"), url, Var.VAR_NULL, Var.VAR_NULL, Var.VAR_NULL));
    System.out.println(consultaEndereco.getObjectAsString());
    results = cronapi.json.Operations.getJsonOrMapField(consultaEndereco, Var.valueOf("results[0]"));
    endereco = cronapi.json.Operations.getJsonOrMapField(results, Var.valueOf("formatted_address"));
    setar_endereco(endereco);
   return Var.VAR_NULL;
   }
 }.call();
}

/**
 *
 * @param endereco
 */
// Descreva esta função...
public static void setar_endereco(Var endereco) throws Exception {
  new Callable<Var>() {

   private Var logradouro = Var.VAR_NULL;
   private Var bairro = Var.VAR_NULL;
   private Var cidade = Var.VAR_NULL;
   private Var exibirCep = Var.VAR_NULL;
   private Var exibirEstado = Var.VAR_NULL;
   private Var pais = Var.VAR_NULL;
   private Var link = Var.VAR_NULL;
   private Var listaEndereco = Var.VAR_NULL;
   private Var localidade = Var.VAR_NULL;
   private Var exibirCidade = Var.VAR_NULL;
   private Var listaBairro = Var.VAR_NULL;
   private Var exibirBairro = Var.VAR_NULL;

   public Var call() throws Exception {
    System.out.println(Var.valueOf("ENDERECO:").getObjectAsString());
    System.out.println(endereco.getObjectAsString());
    listaEndereco = cronapi.list.Operations.getListFromText(endereco,Var.valueOf(","));
    logradouro = cronapi.list.Operations.get(listaEndereco, Var.valueOf(1));
    bairro = cronapi.list.Operations.get(listaEndereco, Var.valueOf(2));
    cidade = cronapi.list.Operations.get(listaEndereco, Var.valueOf(3));
    exibirCep = cronapi.list.Operations.get(listaEndereco, Var.valueOf(4));
    pais = cronapi.list.Operations.get(listaEndereco, Var.valueOf(5));
    System.out.println(Var.valueOf("LINK:").getObjectAsString());
    link = Var.valueOf(Var.valueOf("https://www.google.com/maps/embed/v1/place?key=").toString() + Var.valueOf("AIzaSyA2paKBkuaM15miMJk9wxkdvGz2tzby5y4").toString() + Var.valueOf("&q=").toString() + logradouro.toString() + Var.valueOf("+").toString() + bairro.toString() + Var.valueOf("+").toString() + cidade.toString() + Var.valueOf("+").toString() + pais.toString());
    System.out.println(link.getObjectAsString());
    localidade = cronapi.list.Operations.getListFromText(cidade,Var.valueOf("-"));
    exibirCidade = cronapi.list.Operations.get(localidade, Var.valueOf(1));
    exibirEstado = cronapi.list.Operations.get(localidade, Var.valueOf(2));
    listaBairro = cronapi.list.Operations.getListFromText(bairro,Var.valueOf("-"));
    exibirBairro = cronapi.list.Operations.get(listaBairro, Var.valueOf(2));
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.cidade"), exibirCidade);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.estado"), exibirEstado);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.cep"), exibirCep);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.logradouro"), logradouro);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeValueOfField"), Var.valueOf("vars.bairro"), exibirBairro);
    cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeAttrValue"), Var.valueOf("google-maps"), Var.valueOf("src"), link);
   return Var.VAR_NULL;
   }
 }.call();
}

}

