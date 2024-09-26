<!doctype html>
 
<html>
  <head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    
    <title>${title}</title>
    <meta name="description" content="${description}" />
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.5.0/frappe-gantt.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/frappe-gantt/0.5.0/frappe-gantt.min.js"></script>
    
    <style>${style}</style>
  </head>
  <body>
    <h1>${title}</h1>
    <p>${description}</p>
    
    <div class='gantt-wrap'>
      <svg id="gantt"></svg>
    </div>
    
    <script>${script}</script>
  </body>
</html>