package com.example.app.Routes

import com.example.app.SlickRoutes
import com.example.app.models.{Activity, City}

trait AppRoutes extends SlickRoutes{


  get("/") {
    <html>
      <body>
        <div id="app"></div>
        <script src="/front-end/dist/bundle.js"></script>
      </body>
    </html>
  }

  get("/cities") {
    contentType = formats("json")

    City.getAll
  }
}
