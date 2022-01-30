using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Configuration;
using Miniblog.Core.Models;
using Miniblog.Core.Services;
using Moq;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Tests
{
    public class Tests
    {
        private readonly Mock<IBlogService> _blogServiceMock = new Mock<IBlogService>();
        private readonly Mock<IUserServices> _userServicesMock = new Mock<IUserServices>();
        private readonly Mock<IConfiguration> _configMock = new Mock<IConfiguration>();
        private readonly Mock<IWebHostEnvironment> _envMock = new Mock<IWebHostEnvironment>();
        private readonly Mock<IHttpContextAccessor> _httpContext = new Mock<IHttpContextAccessor>();
        private FileBlogService fileBlog;

        [SetUp]
        public void Setup()
        {
            // Setup for 3 tests for IUserServices
            _userServicesMock.Setup(service => service.ValidateUser("demo", "demo123")).Returns(true);

            // Setup for 12 tests for IBlogService
            _envMock.Setup(m => m.WebRootPath).Returns("C:\\Users\\oprav\\Source\\Repos\\Miniblog.Core\\src\\wwwroot");
            fileBlog = new FileBlogService(_envMock.Object, _httpContext.Object);
        }

        [Test]
        public void ValidateUser_Test_ValidInput()
        {
            // Arrange
            var expected = true;

            // Act
            var user = _userServicesMock.Object.ValidateUser("demo", "demo123");

            // Assert
            Assert.AreEqual(expected, user);
        }

        [Test]
        public void ValidateUser_Test_IncorrectPassword()
        {
            // Arrange
            var expected = true;

            // Act
            var user = _userServicesMock.Object.ValidateUser("demo", "DEMO123");

            // Assert
            Assert.AreNotEqual(expected, user);
        }

        [Test]
        public void ValidateUser_Test_IncorrectUsername()
        {
            // Arrange
            var expected = true;
          

            // Act
            var user = _userServicesMock.Object.ValidateUser("DEMO", "demo123");

            // Assert
            Assert.AreNotEqual(expected, user);
        }

        [Test]
        public void DeletePost_Test_NullPost()
        {
            // Arrange
            Post post = null;
            Exception exception = new ArgumentNullException();
           
            // Act
            Action throwingAction = () =>
            {
                throw new ArgumentNullException();
            };
            Task task = new Task(throwingAction);
            
            // Assert
            Assert.Throws<ArgumentNullException>(() => fileBlog.DeletePost(post));
        }

        [Test]
        public void DeletePost_Test()
        {
            // Arrange
            Post post = new Post
            {
                Content = "<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>&nbsp;</p>",
                Excerpt = "To be deleted",
                ID = "637753570062705944",
                Title = "To be deleted",
            };
            Task task = Task.CompletedTask;

            // Act
            Task result = fileBlog.DeletePost(post);

            // Assert
            Assert.AreEqual(result,task);
        }

        [Test]
        public void GetPosts_Test()
        {
            // Arrange
            var setUpList = new List<Post>();
            setUpList.Add(new Post()
            {
                Content = "<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>&nbsp;</p>",
                Excerpt = "To be deleted",
                ID = "637753568305040275",
                Title = "To be deleted",

            });
            
            // Act
            IAsyncEnumerable<Post>  results = fileBlog.GetPosts();
            var list = results.ToListAsync();

            // Assert
            Assert.AreEqual(setUpList[0].Title, list.Result[2].Title);
            Assert.AreEqual(setUpList[0].Excerpt, list.Result[2].Excerpt);
            Assert.AreEqual(setUpList[0].ID, list.Result[2].ID);
            Assert.AreEqual(setUpList[0].Content, list.Result[2].Content);
        }

        [Test]
        public void GetPosts_Test_TwoInputVariables()
        {
            // Arrange
            int count = 3;
            var setUpList = new List<Post>();
            setUpList.Add(new Post()
            {
                Content = "<p>Very new.</p>\n<p>Fresh.</p>",
                Excerpt = "Newest blog post.",
                ID = "637753626758523868",
                Title = "Something New",

            });
            setUpList.Add(new Post()
            {
                Content = "<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>&nbsp;</p>",
                Excerpt = "To be deleted",
                ID = "637753568305040275",
                Title = "To be deleted",

            });
            
            // Act
            IAsyncEnumerable<Post> results = fileBlog.GetPosts(count);
            var list = results.ToListAsync();

            // Assert
            Assert.AreEqual(setUpList[0].Title, list.Result[1].Title);
            Assert.AreEqual(setUpList[0].Excerpt, list.Result[1].Excerpt);
            Assert.AreEqual(setUpList[0].ID, list.Result[1].ID);
            Assert.AreEqual(setUpList[0].Content, list.Result[1].Content);

            Assert.AreEqual(setUpList[1].Title, list.Result[2].Title);
            Assert.AreEqual(setUpList[1].Excerpt, list.Result[2].Excerpt);
            Assert.AreEqual(setUpList[1].ID, list.Result[2].ID);
            Assert.AreEqual(setUpList[1].Content, list.Result[2].Content);
        }

        [Test]
        public void GetTestById_Test()
        {
            // Arrange
            Post post = new Post
            {
                Content = "<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>&nbsp;</p>",
                Excerpt = "To be deleted",
                ID = "637753568305040275",
                Title = "To be deleted",
            };
            var id = "637753568305040275";
           

            // Act
            Task<Post> results = fileBlog.GetPostById(id);

            // Assert
            Assert.AreEqual(post.Title, results.Result.Title);
            Assert.AreEqual(post.Excerpt, results.Result.Excerpt);
            Assert.AreEqual(post.ID, results.Result.ID);
            Assert.AreEqual(post.Content, results.Result.Content);
        }

        [Test]
        public void GetCategories_Test()
        {
            // Arrange
            string expected = "new";
            
            // Act
            IAsyncEnumerable<string> results = fileBlog.GetCategories();
            var list = results.ToListAsync();

            // Assert
            Assert.AreEqual(expected, list.Result[0]);
        }

        [Test]
        public void GetPostsByCategory_Test()
        {
            // Arrange
            string category = "to be deleted";
            Post post = new Post
            {
                Content = "<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>To be deleted</p>\n<p>&nbsp;</p>",
                Excerpt = "To be deleted",
                ID = "637753568305040275",
                Title = "To be deleted",
            };
            
            // Act
            IAsyncEnumerable<Post> results = fileBlog.GetPostsByCategory(category);
            var list = results.ToListAsync();

            // Assert
            Assert.AreEqual(category, list.Result[0].Categories[0]);
        }

        [Test]
        public void SaveFile_Test_NullBytes()
        {
            // Arrange
            byte[] bytes = null;
            string fileName = "testing";
                        
            // Act & Assert
            Assert.ThrowsAsync<ArgumentNullException>(async () => await fileBlog.SaveFile(bytes,fileName));
        }

        [Test]
        public void SaveFile_Test()
        {
            // Arrange
            byte[] bytes = new byte[3];
            bytes[0] = 1;
            bytes[1] = 22;
            bytes[2] = 203;
            string fileName = "QualityAssurence";
            var expected = "/Posts/files/" + fileName;
            
            // Act
            Task<string> result = fileBlog.SaveFile(bytes, fileName);
            string[] split = result.Result.Split("_");

            // Assert
            Assert.AreEqual(expected, split[0]);
        }

        [Test]
        public void SavePost_Test_NullPost()
        {
            // Arrange
            Post post = null;
            Exception exception = new ArgumentNullException();
            
            // Act & Assert
            Assert.ThrowsAsync<ArgumentNullException>(async () => await fileBlog.SavePost(post));
        }

        [Test]
        public async Task SavePost_TestAsync()
        {
            // Arrange
            Post post = new Post
            {
               Content = "<p>New Movie in Theaters.</p>\n<p>Very Soon.</p>",
               Excerpt = "Newest blog post.",
               ID = "637753633758577868",
               Title = "Breaking News",
            };
           
            // Act
            await fileBlog.SavePost(post);
            Task<Post> result = fileBlog.GetPostById("637753633758577868");

            // Assert
            Assert.AreEqual(post.Title, result.Result.Title);
            Assert.AreEqual(post.Excerpt, result.Result.Excerpt);
            Assert.AreEqual(post.ID, result.Result.ID);
            Assert.AreEqual(post.Content, result.Result.Content);
        }

        [Test]
        public void GetPostBySlug_Test()
        {
            // Arrange
            Post post = new Post
            {
                Content = "<p>Very new.</p>\n<p>Fresh.</p>",
                Excerpt = "Newest blog post.",
                ID = "637753626758523868",
                Title = "Something New",
                Slug = "www.google.com"
            };
            var slug = "www.google.com";
           
            // Act
            Task<Post> results = fileBlog.GetPostBySlug(slug);

            //Assert
            Assert.AreEqual(post.Title, results.Result.Title);
            Assert.AreEqual(post.Excerpt, results.Result.Excerpt);
            Assert.AreEqual(post.ID, results.Result.ID);
            Assert.AreEqual(post.Content, results.Result.Content);
        }
    }
}
