/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Paulo Sigrist
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.sigrist.spotify;

import java.time.LocalDate;

/**
 * Get the current user information.
 * @since 1.0.0
 */
public interface Me {
    /**
     * The user birth date.
     * @return The birth date.
     */
    LocalDate birthDate();

    /**
     * The user country.
     * @return The user country.
     */
    String country();

    /**
     * The user display name.
     * @return The display name.
     */
    String displayName();

    /**
     * The user email.
     * @return The email.
     */
    String email();

    // @xtodo #7 Add other fields.

    // external URL
    // followers
    // href
    // id
    // images
    // product
    // type
    // uri.

    /**
     * The user playlists.
     * @return A {@link Playlists} instance.
     */
    Playlists playlists();
}
