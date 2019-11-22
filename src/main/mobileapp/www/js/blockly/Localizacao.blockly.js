window.blockly = window.blockly || {};
window.blockly.js = window.blockly.js || {};
window.blockly.js.blockly = window.blockly.js.blockly || {};
window.blockly.js.blockly.Localizacao = window.blockly.js.blockly.Localizacao || {};

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Localizacao.definir_rendodeza_via_cep = function(latitude, longitude) {
 var item, consultaEndereco, coordenadas, endereco, exibirBairro, exibirCidade, exibirEstado, google_bairro, google_cep, google_cidade, google_logradouro, google_pais, link, listaBairro, listaEndereco, localidade, opcaoSelecionada, param, results, url, x;
  if (!this.cronapi.maps.isInitialized("google-maps")) {
    this.cronapi.maps.init("google-maps", 'roadmap', this.cronapi.maps.createLatLngPoint(latitude, longitude), '16', function(sender_item) {
        item = sender_item;
      this.cronapi.maps.drawCircle("google-maps", 'IdCirculo', this.cronapi.maps.createLatLngPoint(latitude, longitude), '20', 'gray', 'black', '0.4', '');
      this.cronapi.maps.createMarker("google-maps", 'IdMarcador', 'Seu local', this.cronapi.maps.createLatLngPoint(latitude, longitude), '', '', '');
    }.bind(this));
  }
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Localizacao.limpaCampos = function() {
 var item, consultaEndereco, coordenadas, endereco, exibirBairro, exibirCidade, exibirEstado, google_bairro, google_cep, google_cidade, google_logradouro, google_pais, latitude, link, listaBairro, listaEndereco, localidade, longitude, opcaoSelecionada, param, results, url, x;
  console.log('limpando campos...');
  this.cronapi.screen.changeValueOfField("vars.estado", ' ');
  this.cronapi.screen.changeValueOfField("vars.cidade", ' ');
  this.cronapi.screen.changeValueOfField("vars.bairro", ' ');
  this.cronapi.screen.changeValueOfField("vars.cep", ' ');
  this.cronapi.screen.changeValueOfField("vars.logradouro", ' ');
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Localizacao.obter_endereco = function(coordenadas) {
 var item, consultaEndereco, endereco, exibirBairro, exibirCidade, exibirEstado, google_bairro, google_cep, google_cidade, google_logradouro, google_pais, latitude, link, listaBairro, listaEndereco, localidade, longitude, opcaoSelecionada, param, results, url, x;
  url = ['https://maps.googleapis.com/maps/api/geocode/json?',coordenadas,'&key=AIzaSyA2paKBkuaM15miMJk9wxkdvGz2tzby5y4'].join('');
  this.cronapi.util.getURLFromOthers('GET', 'application/x-www-form-urlencoded', url, null, this.cronapi.json.createObjectFromString(['{\"Access-Control-Allow-Origin\":','\"https://maps.googleapis.com/maps/api/geocode/json?',coordenadas,'&key=AIzaSyA2paKBkuaM15miMJk9wxkdvGz2tzby5y4\"}'].join('')), function(sender_item) {
      item = sender_item;
    console.log('&key=AIzaSyA2paKBkuaM15miMJk9wxkdvGz2tzby5y4');
    consultaEndereco = item;
    console.log(consultaEndereco);
    results = this.cronapi.json.getProperty(consultaEndereco, 'results[0]');
    endereco = this.cronapi.json.getProperty(consultaEndereco, 'formatted_address');
    this.blockly.js.blockly.Localizacao.setar_endereco(endereco);
  }.bind(this), function(sender_item) {
      item = sender_item;
    this.cronapi.screen.notify('error','Não foi possível localizar seu endereço!');
  }.bind(this));
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Localizacao.setar_endereco = function(endereco) {
 var item, consultaEndereco, coordenadas, exibirBairro, exibirCidade, exibirEstado, google_bairro, google_cep, google_cidade, google_logradouro, google_pais, latitude, link, listaBairro, listaEndereco, localidade, longitude, opcaoSelecionada, param, results, url, x;
  listaEndereco = coordenadas.split(',');
  google_logradouro = coordenadas[0];
  google_bairro = coordenadas[1];
  google_cidade = coordenadas[2];
  google_cep = coordenadas[3];
  google_pais = coordenadas[4];
  link = ['https://www.google.com/maps/embed/v1/place?key=','AIzaSyA2paKBkuaM15miMJk9wxkdvGz2tzby5y4','&q=',google_logradouro,'+',google_bairro,'+',google_cidade,'+',google_pais].join('');
  localidade = google_cidade.split('-');
  exibirCidade = localidade[0];
  exibirEstado = localidade[0];
  listaBairro = google_bairro.split('-');
  exibirBairro = listaBairro[1];
  this.cronapi.screen.changeValueOfField("vars.estado", exibirEstado);
  this.cronapi.screen.changeValueOfField("vars.cidade", exibirCidade);
  this.cronapi.screen.changeValueOfField("vars.bairro", exibirBairro);
  this.cronapi.screen.changeValueOfField("vars.logradouro", google_logradouro);
  this.cronapi.screen.changeValueOfField("vars.cep", google_cep);
  this.cronapi.screen.changeAttrValue("google-maps", 'src', link);
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Localizacao.obter_coordenadas = function() {
 var item, consultaEndereco, coordenadas, endereco, exibirBairro, exibirCidade, exibirEstado, google_bairro, google_cep, google_cidade, google_logradouro, google_pais, latitude, link, listaBairro, listaEndereco, localidade, longitude, opcaoSelecionada, param, results, url, x;
  console.log('sucesso');
  this.cronapi.cordova.geolocation.getCurrentPosition(function(sender_item) {
      item = sender_item;
    console.log('sucesso');
    console.log(item);
    coordenadas = ['latlng=',this.cronapi.object.getProperty(item, 'coords.latitude'),',',this.cronapi.object.getProperty(item, 'coords.longitude')].join('');
    param = String(coordenadas);
    latitude = this.cronapi.object.getProperty(item, 'coords.latitude');
    longitude = this.cronapi.object.getProperty(item, 'coords.longitude');
    console.log(String('coordernadas: ') + String(coordenadas));
    this.cronapi.util.callServerBlocklyNoReturn('blockly.PegaTexto:obter_endereco', param);
    if (!this.cronapi.maps.isInitialized("google-maps")) {
      this.cronapi.maps.init("google-maps", 'roadmap', this.cronapi.maps.createLatLngPoint(latitude, longitude), '16', function(sender_item) {
          item = sender_item;
        this.cronapi.maps.drawCircle("google-maps", 'IdCirculo', this.cronapi.maps.createLatLngPoint(latitude, longitude), '20', 'gray', 'black', '0.4', '');
        this.cronapi.maps.createMarker("google-maps", 'IdMarcador', 'Seu local', this.cronapi.maps.createLatLngPoint(latitude, longitude), '', '', '');
      }.bind(this));
    }
  }.bind(this), function(sender_item) {
      item = sender_item;
    console.log('error');
  }.bind(this));
}

/**
 * Descreva esta função...
 */
window.blockly.js.blockly.Localizacao.seleciona_opcao_endereco = function() {
 var item, consultaEndereco, coordenadas, endereco, exibirBairro, exibirCidade, exibirEstado, google_bairro, google_cep, google_cidade, google_logradouro, google_pais, latitude, link, listaBairro, listaEndereco, localidade, longitude, opcaoSelecionada, param, results, url, x;
  console.log('abc');
  opcaoSelecionada = this.cronapi.screen.getValueOfField("vars.combobox6705");
  if (opcaoSelecionada == 2) {
    console.log('abc');
    this.blockly.js.blockly.Localizacao.obter_coordenadas();
  }
}
