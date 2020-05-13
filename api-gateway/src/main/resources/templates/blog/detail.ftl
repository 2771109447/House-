
<!DOCTYPE html>

<html lang="en-US">
<@common.header/>

<body class="page-sub-page page-blog-detail" id="page-top">
<div class="wrapper">
    <@common.nav/>

    <div id="page-content">
        <div class="container">
            <ol class="breadcrumb">
                <li><a href="/blog/list">Home</a></li>
                <li class="active">博客详情</li>
            </ol>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-9 col-sm-9">
                    <section id="content">
                        <article class="blog-post">
                            <header><a href="#"><h2>${blog.title}</h2></a></header>
                            <figure class="meta">
                                <a href="#" class="link-icon"><i class="fa fa-user"></i>陈志平</a>
                                <a href="#" class="link-icon"><i class="fa fa-calendar"></i><td>${(blog.createTime?datetime)}</td></a>
                                <div class="tags">
                                   <#list blog.tagList as tag> 
                                    <a href="#" class="tag article">${tag}</a>
                                  </#list>
                                </div>
                            </figure>
                            ${blog.content}
                        </article>
                        
                    </section>
                   <section id="comments">
                                    <div class="agent-form">
                                                    <form role="form" id="form-contact-agent" method="post" action="/comment/addBlogComment" class="clearfix">
                                                        <input type="hidden" name="blogId" value="${blog.id}">
                                                        <div class="form-group">
                                                            <label for="form-contact-agent-message">评论</label>
                                                            <textarea class="form-control" id="form-contact-agent-message" rows="2" name="content" required></textarea>
                                                        </div>
                                                        <div class="form-group">
                                                            <button type="submit" class="btn pull-right btn-default" id="form-contact-agent-submit">评论</button>
                                                        </div>
                                                        <div id="form-contact-agent-status"></div>
                                                    </form>
                                    </div>
                                    <header><h2 class="no-border">精彩评论</h2></header>
                                    <ul class="comments">
                                      <#list commentList as comment> 
                                        <li class="comment" style="width: 830px;">
                                            <figure>
                                                <div class="image">
                                                    <img alt="" src="${comment.avatar}">
                                                </div>
                                            </figure>
                                            <div class="comment-wrapper">
                                                <div class="name pull-left">${comment.userName}</div>
                                                <span class="date pull-right"><span class="fa fa-calendar"></span>${(comment.createTime)?datetime}</span>
                                                <p>${comment.content}
                                                </p>
                                                <hr>
                                            </div>
                                        </li>
                                      </#list>
                                    </ul>
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