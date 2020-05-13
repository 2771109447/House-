<!DOCTYPE html>

<html lang="en-US">
 <@common.header/>

<body class="page-sub-page page-agents-listing" id="page-top">
<div class="wrapper">
    <@common.nav/>
    <div id="page-content">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li><a href="#">经纪人</a></li>
                <li class="active">经纪人列表</li>
            </ol>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <section id="agents-listing">
                        <header><h1>经纪人列表</h1></header>
                        <div class="row">
                          <#list ps.list as agent>
                            <div class="col-md-12 col-lg-6" >
                                <div class="agent">
                                    <a href="/agency/agentDetail?id=${agent.id!}" class="agent-image"><img alt="" src="${agent.avatar}"></a>
                                    <div class="wrapper">
                                        <header><a href="/agency/agentDetail?id=${agent.id!}"><h2>${agent.userName!}</h2></a></header>
                                        <dl>
                                            <dt>Phone:</dt>
                                            <dd>${agent.phone!}</dd>
                                            <dt>Email:</dt>
                                            <dd><a href="mailto:#">${agent.email!}</a></dd>
                                            <dt>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</dt>
                                            <dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</dd>
                                            <dt>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</dt>
                                            <dd>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</dd>
                                        </dl>
                                    </div>
                                </div>
                            </div>

                          </#list>
                        </div>
                    </section>

                    <div class="center">
                        <@common.paging ps.pagination/>
                    </div>
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