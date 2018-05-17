/**
 * Copyright (c) 2018, Mihai Emil Andronache
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1)Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 2)Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 3)Neither the name of docker-java-api nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.amihaiemil.docker;

import com.amihaiemil.docker.mock.AssertRequest;
import com.amihaiemil.docker.mock.Response;
import java.net.URI;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Unit tests for {@link RemoteDocker}.
 * @author George Aristy (george.aristy@gmail.com)
 * @version $Id$
 * @since 0.0.1
 * @checkstyle MethodName (500 lines)
 */

public final class RemoteDockerTestCase {
    
    
    /**
     * RemoteDocker can return its version.
     */
    @Test
    public void returnsVersion() {
        MatcherAssert.assertThat(
            new RemoteDocker(
                Mockito.mock(HttpClient.class),
                URI.create("http://localhost")
            ).version(),
            Matchers.equalTo("v1.35")
        );
        
        MatcherAssert.assertThat(
            new RemoteDocker(
                Mockito.mock(HttpClient.class),
                URI.create("http://localhost"),
                "v1.37"
            ).version(),
            Matchers.equalTo("v1.37")
        );
    }
    
    /**
     * Ping must be TRUE if response is OK.
     * @throws Exception If an error occurs.
     */
    @Test
    public void pingTrueIfResponseIsOk() throws Exception {
        MatcherAssert.assertThat(
            new RemoteDocker(
                new AssertRequest(
                    new Response(HttpStatus.SC_OK, "")
                ),
                URI.create("http://remotedocker")
            ).ping(),
            Matchers.is(true)
        );
    }

    /**
     * Ping must be False if response is not OK.
     * @throws Exception If an error occurs.
     */
    @Test
    public void pingFalseIfResponseIsNotOk() throws Exception {
        MatcherAssert.assertThat(
            new RemoteDocker(
                new AssertRequest(
                    new Response(HttpStatus.SC_NOT_FOUND, "")
                ),
                URI.create("http://remotedocker")
            ).ping(),
            Matchers.is(false)
        );
    }

    /**
     * RemoteDocker can return the Containers.
     */
    @Test
    public void getsContainers() {
        MatcherAssert.assertThat(
            new RemoteDocker(
                Mockito.mock(HttpClient.class),
                URI.create("http://localhost")
            ).containers(),
            Matchers.notNullValue()
        );
    }

    /**
     * RemoteDocker can return the Swarm.
     */
    @Test
    public void returnsSwarm() {
        MatcherAssert.assertThat(
            new RemoteDocker(
                Mockito.mock(HttpClient.class),
                URI.create("http://localhost")
            ).swarm(),
            Matchers.notNullValue()
        );
    }
    
    
    /**
     * RemoteDocker can return Images.
     */
    @Test
    public void returnsImages() {
        MatcherAssert.assertThat(
            new RemoteDocker(
                Mockito.mock(HttpClient.class),
                URI.create("http://localhost")
            ).images(),
            Matchers.notNullValue()
        );
    }
}
