var ubicacionesRouter = Backbone.Router.extend({
  extensionViewInstance: null,
  autorViewInstance: null,
  initialize: function() {
  },
  routes: {
    "": "ubicacion",
    "*actions" : "default",
  },
  ubicacion: function() {
    if(this.ubicacicionView == null){
      this.ubicacicionView = new UbicacionView();
    }
    this.ubicacicionView.render();
    this.ubicacicionView.tablaDepartamento.listar();
  },
  default: function() {
    window.location.href = BASE_URL + "ubicaciones/#/error/404";
  },
});

$(document).ready(function(){
  router = new ubicacionesRouter();
  Backbone.history.start();
})
