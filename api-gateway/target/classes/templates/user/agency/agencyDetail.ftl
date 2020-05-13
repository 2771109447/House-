<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-agency-detail" id="page-top">
<div class="wrapper">
     <@common.nav/>

    <div id="page-content">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">经纪机构</a></li>
                <li class="active">机构详情</li>
            </ol>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <section id="agent-detail">
                        <header><h1>${agency.name}</h1></header>
                        <section id="agent-info">
                            <div class="row">
                                <div class="col-md-3 col-sm-3">
                                    <figure class="agency-image"><img alt="" src="/static/assets/img/agency-logo-big-01.png"></figure>
                                </div>
                                <div class="col-md-5 col-sm-5">
                                    <h3>联系信息</h3>
                                    <address>
                                        ${agency.address}
                                    </address>
                                    <dl>
                                        <dt>手机:</dt>
                                        <dd>${agency.phone}</dd>
                                        <dt>电话:</dt>
                                        <dd>${agency.mobile}</dd>
                                        <dt>Email:</dt>
                                        <dd><a href="mailto:#">${agency.email}</a></dd>
                                        <dt>官网:</dt>
                                        <dd>${agency.webSite}</dd>
                                    </dl>
                                </div>
                                <div class="col-md-4 col-sm-4">
                                    <h3>简单介绍</h3>
                                    <p>${agency.aboutUs} 
                                    </p>
                                    <div id="social">
                                        <h3>社会概况</h3>
                                        <div class="agent-social">
                                            <a href="#" class="fa fa-twitter btn btn-grey-dark"></a>
                                            <a href="#" class="fa fa-facebook btn btn-grey-dark"></a>
                                            <a href="#" class="fa fa-linkedin btn btn-grey-dark"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">

                            </div>
                        </section>
                      
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

<@common.js/>
<script type="text/javascript" src="/assets/js/ie.js"></script>
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