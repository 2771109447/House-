
<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-blog-listing" id="page-top">
<div class="wrapper">
     <@common.nav/>

    <div id="page-content">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li class="active">博客列表</li>
            </ol>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <section id="content">
                        <header><h1>博客列表</h1></header>
                        
                     <#list ps.list as blog> 
                        <article class="blog-post">
                            <header><a href="/blog/detail?id=${blog.id}"><h2>${blog.title}</h2></a></header>
                            <figure class="meta">
                                <a href="#" class="link-icon"><i class="fa fa-user"></i>管理员</a>
                                <a href="#" class="link-icon"><i class="fa fa-calendar"></i><td>${(blog.createTime)?date}</td></a>
                                <div class="tags">
                                   <#list blog.tagList as tag> 
                                    <a href="#" class="tag article">${tag}</a>
                                  </#list>
                                </div>
                            </figure>
                            <p>
                               ${blog.digest}
                            </p>
                            <a href="/blog/detail?id=${blog.id}" class="link-arrow">阅读更多</a>
                        </article>
                    </#list>

                        <div class="center">
                              <@common.paging ps.pagination/>
                        </div>
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
<script type="text/javascript" src="/static/assets/js/ie.js"></script>
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