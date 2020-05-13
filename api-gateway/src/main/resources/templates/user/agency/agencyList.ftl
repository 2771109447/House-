<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-agencies-listing" id="page-top">
<div class="wrapper">
     <@common.nav/>

    <div id="page-content">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li class="active">经纪机构</li>
            </ol>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <section id="agencies-listing">
                        <header><h1>经纪机构</h1></header>
                        <#list agencyList as agency>
                           <div class="agency">
                            <a href="/agency/agencyDetail?id=${agency.id}" class="agency-image"><img alt="" src="/static/assets/img/agency-logo-02.png"></a>
                            <div class="wrapper">
                                <header><a href="/agency/agencyDetail?id=${agency.id}"><h2>${agency.name}</h2></a></header>
                                <dl>
                                    <dt>手机:</dt>
                                    <dd>${agency.phone}</dd>
                                    <dt>电话:</dt>
                                    <dd>${agency.mobile}</dd>
                                    <dt>Email:</dt>
                                    <dd><a href="mailto:#">${agency.email}</a></dd>
                                </dl>
                                <address>
                                    <strong>地址</strong>
                                    <br>
                                    <strong>${agency.name}</strong><br>
                                    ${agency.address}
                                </address>
                            </div>
                          </div>
                        </#list>
                    </section>
                </div>
                <div class="col-md-3 col-sm-3">
                    <section id="sidebar">
                        <@common.search />
                        <@common.hot />
                    </section>
                </div>

            </div>
        </div>
    </div>

     <@common.footer/>
</div>


<script type="text/javascript" src="assets/js/ie.js"></script>
<@common.js/>
 <script  type="text/javascript" >
     

     $(document).ready(function() {
          var errorMsg   = "${errorMsg!""}";
          var successMsg = "${successMsg!""}";
          if(errorMsg){ 
              errormsg("error",errorMsg);
          }
          if(successMsg) {
              successmsg("success",successMsg);
          }
        })
        
 </script>

</body>
</html>