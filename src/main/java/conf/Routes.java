/**
 * Copyright (C) 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package conf;

import ninja.AssetsController;
import ninja.Router;
import ninja.Results;
import ninja.application.ApplicationRoutes;
import controllers.HomeController;
import controllers.LoginController;
import controllers.ubicaciones.DepartamentoController;
import controllers.UbicacionesController;
import controllers.ErrorController;

public class Routes implements ApplicationRoutes {
  @Override
  public void init(Router router) {  
    //login
    router.GET().route("/login").with(LoginController::index);
    router.POST().route("/login/acceder").with(LoginController::acceder);
    router.GET().route("/login/ver").with(LoginController::ver);
    router.GET().route("/login/cerrar").with(LoginController::cerrar);
    //errores
    router.GET().route("/error/access/{numero}").with(ErrorController::access);
    //home
    router.GET().route("/").with(HomeController::index);
    //ubicaciones
    router.GET().route("/ubicaciones").with(() -> Results.redirect("/ubicaciones/#"));
    router.GET().route("/ubicaciones/").with(UbicacionesController::index);
    //servicios REST
    router.GET().route("/departamento/listar").with(DepartamentoController::listarDB);
    router.POST().route("/departamento/guardar").with(DepartamentoController::guardar);
    
    ///////////////////////////////////////////////////////////////////////
    // Assets (pictures / javascript)
    ///////////////////////////////////////////////////////////////////////    
    //router.GET().route("/assets/webjars/{fileName: .*}").with(AssetsController::serveWebJars);
    router.GET().route("/assets/{fileName: .*}").with(AssetsController::serveStatic);
    ///////////////////////////////////////////////////////////////////////
    // Index / Catchall shows index page
    ///////////////////////////////////////////////////////////////////////
    router.GET().route("/.*").with(() -> Results.redirect("/error/access/404"));
    router.POST().route("/.*").with(ErrorController::errorPOST);
  }
}
