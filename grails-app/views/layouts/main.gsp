<!DOCTYPE html>
<html>
<head>
    <title><g:message code="application.title"/> - <g:layoutTitle/></title>
    <r:require module="application"/>
    <r:layoutResources/>
    <g:layoutHead/>
</head>

<body>
<tmpl:/templates/navbar/>
<div id="wrap">
    <div class="container">
        <tmpl:/templates/messages/>
        <g:layoutBody/>
    </div>
</div>

<div id="footer">
    <div class="container">
        <p class="text-muted credit">
        &copy; 2013, Yuriy Lobko.
        </p>
    </div>
</div>
<r:layoutResources/>
</body>
</html>